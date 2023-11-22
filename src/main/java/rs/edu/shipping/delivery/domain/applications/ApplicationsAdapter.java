package rs.edu.shipping.delivery.domain.applications;

import java.math.BigDecimal;
import java.util.Date;
import rs.edu.shipping.delivery.controller.request.DeliveryRequest;
import rs.edu.shipping.delivery.domain.Provider;
import rs.edu.shipping.delivery.price.Price;

public class ApplicationsAdapter extends Provider {
  Wolt wolt;
  Glovo glovo;

  public ApplicationsAdapter(Wolt wolt) {
    this.wolt = wolt;
  }

  public ApplicationsAdapter(Glovo glovo) {
    this.glovo = glovo;
  }

  @Override
  public String name() {
    if(glovo==null){
      return wolt.name();
    } else if (wolt == null) {
      return glovo.name();
    } else return null;
  }

  @Override
  public BigDecimal courierFee() {
    if(glovo==null){
      return wolt.courierFee();
    } else if (wolt == null) {
      return glovo.courierFee();
    } else return null;
  }

  @Override
  public BigDecimal price(DeliveryRequest request) {
    if(glovo==null){
      return wolt.weatherFee();
    } else if (wolt == null) {
      return glovo.weatherFee();
    } else return null;
  }

  @Override
  public Date deliveryDate(String cityFrom, String cityTo) {
    return new Date();
  }

  @Override
  public Price weight(BigDecimal weight, Price price) {
    return price;
  }
}
