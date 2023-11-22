package rs.edu.shipping.delivery.domain;

import java.math.BigDecimal;
import java.util.Date;
import rs.edu.shipping.config.DeliveryTimeConfig;
import rs.edu.shipping.config.PriceConfig;
import rs.edu.shipping.config.StaticApplicationContext;
import rs.edu.shipping.delivery.controller.request.DeliveryRequest;
import rs.edu.shipping.delivery.price.CityShipping;
import rs.edu.shipping.delivery.price.IntercityShipping;
import rs.edu.shipping.delivery.price.Price;
import rs.edu.shipping.delivery.service.CourierService;
import rs.edu.shipping.delivery.service.ProviderService;

public abstract class Provider {
  String name;
  BigDecimal courierFee;
  BigDecimal price;
  Date deliveryDate;

  public String name(){
    return name;
  }
  public BigDecimal courierFee(){ return courierFee; }
  public BigDecimal price(){return price;}
  public Date deliveryDate(){
    return deliveryDate;
  }

  public BigDecimal price(DeliveryRequest request){return price;}
  public Date deliveryDate(String cityFrom, String cityTo){
    return deliveryDate;
  }

  public final Price calculatePrice(String cityFrom, String cityTo, BigDecimal weight){
    Price price;
    price = distancePrice(cityFrom, cityTo);
    price = weight(weight, price);
    return price;
  }

  public abstract Price weight(BigDecimal weight, Price price);

  Price distancePrice(String cityFrom, String cityTo){
    if(cityFrom.equals(cityTo)){
      return new CityShipping();
    } else{
      return new IntercityShipping();
    }
  }

  public ProviderService getProviderService(){
    return (ProviderService) StaticApplicationContext.getContext().getBean("providerService");
  }
  public CourierService getCourierService(){
    return (CourierService) StaticApplicationContext.getContext().getBean("courierService");
  }
  public DeliveryTimeConfig getDeliveryTimeConfig(){
    return (DeliveryTimeConfig) StaticApplicationContext.getContext().getBean("deliveryTimeConfig");
  }
  public PriceConfig getPriceConfig(){
    return (PriceConfig) StaticApplicationContext.getContext().getBean("priceConfig");
  }

}
