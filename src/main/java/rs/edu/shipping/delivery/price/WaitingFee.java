package rs.edu.shipping.delivery.price;

import java.math.BigDecimal;

public class WaitingFee extends PriceDecorator {
  Price price;

  public WaitingFee(Price price) {
    this.price = price;
  }

  @Override
  public BigDecimal price() {
    return price.price().add(BigDecimal.valueOf(50));
  }

  @Override
  public String getDescription() {
    return price.description + "Extra fee for waiting more than 5min.";
  }

}
