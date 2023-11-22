package rs.edu.shipping.delivery.domain.courier;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import rs.edu.shipping.delivery.constants.ProviderName;
import rs.edu.shipping.delivery.controller.request.DeliveryRequest;
import rs.edu.shipping.delivery.domain.Provider;
import rs.edu.shipping.delivery.price.Price;
import rs.edu.shipping.delivery.price.WeightFee;

public class Aks extends Provider {

  @Override
  public String name() {
    return ProviderName.AKS.getValue();
  }

  @Override
  public BigDecimal courierFee() {
    return getProviderService().getByName(ProviderName.AKS.name()).get().getCourierFee();
  }

  @Override
  public Price weight(BigDecimal weight, Price price) {
    if(weight.compareTo(getPriceConfig().getWeightLimitAks()) == 1) {
      return new WeightFee(price);
    }
    return price;
  }

  //RULE: Price is same for deliveries in same city and between cities.
  // Extra fee for extra weight
  @Override
  public BigDecimal price(DeliveryRequest request){
    return super.calculatePrice(request.getCityFrom(), request.getCityFrom(), request.getWeight()).price();
  }

  //RULE: If there is available couriers delivery time is default - 2 days
  //If there is no available couriers we're looking for first available courier
  //and rule is courier.day + 1
  //NO MATTER if delivery is in same city or between cities
  @Override
  public Date deliveryDate(String cityFrom, String cityTo) {
    Calendar c = Calendar.getInstance();
    var availableCouriers = getCourierService().getAvailableByProvider(name());

    if(availableCouriers.size() > 0){
      c.setTime(new Date());
      c.add(Calendar.DATE, getDeliveryTimeConfig().getDefaultAks());
    } else{
      c.setTime(getCourierService().getEarliestDeliveryDate(name()));
      c.add(Calendar.DATE, getDeliveryTimeConfig().getDelayAks());
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String output = sdf.format(c.getTime());
    try {
      return sdf.parse(output);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

}
