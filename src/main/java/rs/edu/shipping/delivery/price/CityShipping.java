package rs.edu.shipping.delivery.price;

import java.math.BigDecimal;
import rs.edu.shipping.config.PriceConfig;
import rs.edu.shipping.config.StaticApplicationContext;

public class CityShipping extends Price {

  public CityShipping() {
    PriceConfig priceConfig = (PriceConfig) StaticApplicationContext.getContext().getBean("priceConfig");
    this.amount = priceConfig.getCityShippingAmount();
    this.description = priceConfig.getCityShippingDescription();
  }

  @Override
  public BigDecimal price() {
    return amount;
  }

}
