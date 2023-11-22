package rs.edu.shipping.delivery.price;

import java.math.BigDecimal;
import rs.edu.shipping.delivery.controller.request.DeliveryRequest;

public abstract class PriceItem {

  public final void calculatePriceCity(DeliveryRequest request){
    Price price;
    price = cityShipping(request.getCityFrom(), request.getCityTo());
    price = weight(request.getWeight(), price);
  }

  public final void calculatePriceIntercity(DeliveryRequest request){
    Price price;
    price = cityShipping(request.getCityFrom(), request.getCityTo());
    price = intercityShipping(request.getCityFrom(), request.getCityTo());
    price = weight(request.getWeight(), price);
  }

  public abstract Price weight(BigDecimal weight, Price price);

  Price cityShipping(String cityFrom, String cityTo){
    if(cityFrom.equals(cityTo)){
      return new CityShipping();
    }
    return null;
  }

  Price intercityShipping(String cityFrom, String cityTo){
    if(!cityFrom.equals(cityTo)){
      return new IntercityShipping();
    }
    return null;
  }

}
