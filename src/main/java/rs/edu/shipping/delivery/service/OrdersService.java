package rs.edu.shipping.delivery.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import rs.edu.shipping.delivery.constants.ProviderName;
import rs.edu.shipping.delivery.controller.request.DeliveryRequest;
import rs.edu.shipping.delivery.controller.request.OfferRequest;
import rs.edu.shipping.delivery.controller.request.OrderRequest;
import rs.edu.shipping.delivery.controller.response.OfferResponse;
import rs.edu.shipping.delivery.display.data.AksDisplay;
import rs.edu.shipping.delivery.display.data.BexDisplay;
import rs.edu.shipping.delivery.display.data.DexpressDisplay;
import rs.edu.shipping.delivery.display.data.GlovoDisplay;
import rs.edu.shipping.delivery.display.ShippingData;
import rs.edu.shipping.delivery.display.data.WoltDisplay;
import rs.edu.shipping.delivery.domain.ApplicationDelivery;
import rs.edu.shipping.delivery.domain.CourierDelivery;
import rs.edu.shipping.delivery.domain.Provider;
import rs.edu.shipping.delivery.domain.ProviderCreator;
import rs.edu.shipping.delivery.entity.OrdersEntity;
import rs.edu.shipping.delivery.repository.OrdersEntityRepository;
import rs.edu.shipping.kafka.Consumer;
import rs.edu.shipping.kafka.Producer;
import rs.edu.shipping.user.User;
import rs.edu.shipping.user.service.UserService;

@Service
@RequiredArgsConstructor
public class OrdersService {

  private final OrdersEntityRepository ordersEntityRepository;
  private final UserService userService;
  private final CourierService courierService;
  private final Producer producer;
  private final Consumer consumer;

  //CRUD

  public void create(OrderRequest request) {
    var order = ordersEntityRepository.save(OrdersEntity.builder()
        .locationFrom(request.getLocationFrom())
        .cityFrom(request.getCityFrom())
        .locationTo(request.getLocationTo())
        .cityTo(request.getCityTo())
        .weight(request.getWeight())
        .deliveryDate(request.getDeliveryDate())
        .active(false)
        .user(userService.getByUsername(User.getInstance().getUsername()))
        .build());
    request.setId(order.getId());
    notifyCourier(request);
  }

  public OrdersEntity get(Integer id) {
    return ordersEntityRepository.findById(id).get();
  }

  public void update(OrdersEntity order) {
    ordersEntityRepository.save(order);
  }

  public void deactivateExpiredOrders() {
    Date today = new Date();
    SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
    String stringDate = DateFor.format(today);
    Date date = null;
    try {
      date = DateFor.parse(stringDate);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
    ordersEntityRepository.deactivateExpiredOrders(date);
  }

  public List<OfferResponse> requestOffer(OfferRequest offerRequest) {
    DeliveryRequest request = DeliveryRequest.builder()
        .cityFrom(offerRequest.getCityFrom())
        .cityTo(offerRequest.getCityTo())
        .weight(offerRequest.getWeight())
        .build();
    var providers = createProdivider(request);
    List<OfferResponse> response = new ArrayList<>();
    for (Provider provider : providers) {
      response.add(OfferResponse.builder()
          .provider(provider.name())
          .price(provider.price())
          .deliveryDate(provider.deliveryDate())
          .build());
    }
    return response;
  }

  private List<Provider> createProdivider(DeliveryRequest request) {
    ProviderCreator courier = new CourierDelivery();
    ProviderCreator application = new ApplicationDelivery();
    return List.of(
        courier.selectProvider(request, ProviderName.AKS.getValue()),
        courier.selectProvider(request, ProviderName.BEX.getValue()),
        courier.selectProvider(request, ProviderName.DEXPRESS.getValue()),
        application.selectProvider(request, ProviderName.GLOVO.getValue()),
        application.selectProvider(request, ProviderName.WOLT.getValue())
    );
  }

  public void notifyCourier(OrderRequest request) {
    JSONObject json = new JSONObject();
    json.put("id", request.getId());
    json.put("locationFrom", request.getLocationFrom());
    json.put("locationTo", request.getLocationTo());
    json.put("cityFrom", request.getCityFrom());
    json.put("cityTo", request.getCityTo());
    json.put("weight", request.getWeight());
    json.put("user", User.getInstance().getUsername());
    producer.sendMessage(request.getProvider(), json.toString());
  }

  public void getMessage() {
    ConsumerRecord<String, String> record = consumer.getMessages();
    var message = record.value();
    var provider = record.key();
    if(provider != null) {
      JSONParser parser = new JSONParser();
      JSONObject json = null;
      try {
        json = (JSONObject) parser.parse(message);
      } catch (org.json.simple.parser.ParseException e) {
        throw new RuntimeException(e);
      }
      var order = get(Integer.parseInt(json.get("id").toString()));
      if (Arrays.stream(ProviderName.values()).anyMatch(a -> a.getValue().equals(provider))) {

        ShippingData shippingData = new ShippingData();
        if (provider.equals(ProviderName.AKS.getValue())) {
          new AksDisplay(shippingData);
        } else if (provider.equals(ProviderName.BEX.getValue())) {
          new BexDisplay(shippingData);
        } else if (provider.equals(ProviderName.DEXPRESS.getValue())) {
          new DexpressDisplay(shippingData);
        } else if (provider.equals(ProviderName.GLOVO.getValue())) {
          new GlovoDisplay(shippingData);
        } else {
          new WoltDisplay(shippingData);
        }
        shippingData.setData(order.getLocationFrom(), order.getLocationTo(), order.getCityFrom(),
            order.getCityTo(), order.getWeight(), User.getInstance().getUsername());
      }
    }
  }

  public void chooseOrder(Integer id){
    var order = get(id);
    order.setActive(true);
    order.setCourier(courierService.getByUsername(User.getInstance().getUsername()));
    update(order);
  }
}
