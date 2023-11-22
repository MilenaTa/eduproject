package rs.edu.shipping.delivery.price;

import java.math.BigDecimal;
import rs.edu.shipping.config.PriceConfig;
import rs.edu.shipping.config.StaticApplicationContext;

public class WeightFee extends PriceDecorator {
  Price price;

  public WeightFee(Price price) {
    this.price = price;
    PriceConfig priceConfig = (PriceConfig) StaticApplicationContext.getContext().getBean("priceConfig");
    this.amount = priceConfig.getWeightFeeAmount();
    this.description = priceConfig.getWeightFeeDescription();
  }

  @Override
  public BigDecimal price() {
    return price.price().add(amount);
  }

  @Override
  public String getDescription() {
    return price.description + description;
  }
}
