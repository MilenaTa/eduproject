package rs.edu.shipping.delivery.price;

import java.math.BigDecimal;
import rs.edu.shipping.config.PriceConfig;
import rs.edu.shipping.config.StaticApplicationContext;

public class IntercityShipping extends Price {


  public IntercityShipping() {
    PriceConfig priceConfig = (PriceConfig) StaticApplicationContext.getContext().getBean("priceConfig");
    this.amount = priceConfig.getIntercityShippingAmount();
    this.description = priceConfig.getIntercityShippingDescription();
  }

  @Override
  public BigDecimal price() {
    return amount;
  }

}
