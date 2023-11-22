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


public class Bex extends Provider {

  @Override
  public String name() {
    return ProviderName.BEX.name();
  }

  @Override
  public BigDecimal courierFee() {
    return getProviderService().getByName(ProviderName.BEX.name()).get().getCourierFee();
  }

  @Override
  public Price weight(BigDecimal weight, Price price) {
    return price;
  }

  //RULE: Price is different for deliveries in same city and between cities.
  // No extra fee for extra weight
  @Override
  public BigDecimal price(DeliveryRequest request){
    return super.calculatePrice(request.getCityFrom(), request.getCityTo(), request.getWeight()).price();
  }

  //RULE: If there is available couriers delivery time is:
  // * 2 days for same city
  // * 4 days between cities
  //If there is no available couriers we're looking for first available courier and rule is
  // * courier.day + 2 for same city
  // * courier.day + 4 between cities
  @Override
  public Date deliveryDate(String cityFrom, String cityTo) {
    Calendar c = Calendar.getInstance();
    var availableCouriers = getCourierService().getAvailableByProvider(name());

    if(availableCouriers.size() > 0){
      c.setTime(new Date());
      if(cityFrom.equals(cityTo)){
        c.add(Calendar.DATE, getDeliveryTimeConfig().getDefaultCityBex());
      } else{
        c.add(Calendar.DATE, getDeliveryTimeConfig().getDefaultIntercityBex());
      }
    } else {
      c.setTime(getCourierService().getEarliestDeliveryDate(name()));
      if(cityFrom.equals(cityTo)){
        c.add(Calendar.DATE, getDeliveryTimeConfig().getDelayCityBex());
      } else{
        c.add(Calendar.DATE, getDeliveryTimeConfig().getDelayIntercityBex());
      }
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
