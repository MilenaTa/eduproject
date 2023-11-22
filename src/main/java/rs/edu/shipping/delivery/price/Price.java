package rs.edu.shipping.delivery.price;

import java.math.BigDecimal;

public abstract class Price {
  String description = "";
  BigDecimal amount;

  public String getDescription() {
    return description;
  }
  public abstract BigDecimal price();

}
