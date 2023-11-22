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

public class Dexpress extends Provider {

  @Override
  public String name() {
    return ProviderName.DEXPRESS.name();
  }

  @Override
  public BigDecimal courierFee() {
    return getProviderService().getByName(ProviderName.DEXPRESS.name()).get().getCourierFee();
  }

  @Override
  public Price weight(BigDecimal weight, Price price) {
    if(weight.compareTo(getPriceConfig().getWeightLimitDexpress()) == 1) {
      return new WeightFee(price);
    }
    return price;
  }

  //RULE: Price is different for deliveries in same city and between cities.
  // Extra fee for extra weight
  @Override
  public BigDecimal price(DeliveryRequest request){
    return super.calculatePrice(request.getCityFrom(), request.getCityTo(), request.getWeight()).price();
  }

  //RULE: If there is available couriers delivery time is:
  // * 3 days for same city
  // * 5 days between cities
  //If there is no available couriers we're looking for first available courier:
  // 1) first available in 5 days - rule is
  // * courier.day + 2 for same city
  // * courier.day + 4 between cities
  // 2) first available in more than 5 days - rule is
  // * courier.day + 1 for same city
  // * courier.day + 3 between cities
  @Override
  public Date deliveryDate(String cityFrom, String cityTo) {
    Calendar c = Calendar.getInstance();
    var availableCouriers = getCourierService().getAvailableByProvider(name());

    if(availableCouriers.size() > 0){
      c.setTime(new Date());
      if(cityFrom.equals(cityTo)){
        c.add(Calendar.DATE, getDeliveryTimeConfig().getDefaultCityDexpress());
      } else{
        c.add(Calendar.DATE, getDeliveryTimeConfig().getDefaultIntercityDexpress());
      }
    } else {
      var earliestDeliveryDate = getCourierService().getEarliestDeliveryDate(name());
      c.setTime(earliestDeliveryDate);
      if((earliestDeliveryDate.getTime() - (new Date()).getTime()) > 5){
        if(cityFrom.equals(cityTo)){
          c.add(Calendar.DATE, getDeliveryTimeConfig().getMoreDelayCityDexpress());
        } else{
          c.add(Calendar.DATE, getDeliveryTimeConfig().getMoreDelayIntercityDexpress());
        }
      } else{
        if(cityFrom.equals(cityTo)){
          c.add(Calendar.DATE, getDeliveryTimeConfig().getDelayCityDexpress());
        } else{
          c.add(Calendar.DATE, getDeliveryTimeConfig().getDelayIntercityDexpress());
        }
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
