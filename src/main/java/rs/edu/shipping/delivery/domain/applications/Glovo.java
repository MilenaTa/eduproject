package rs.edu.shipping.delivery.domain.applications;

import java.math.BigDecimal;
import rs.edu.shipping.delivery.constants.ProviderName;
import rs.edu.shipping.delivery.domain.Provider;
import rs.edu.shipping.delivery.price.Price;

public class Glovo extends Provider {

  public String name() {
    return ProviderName.GLOVO.getValue();
  }

  public BigDecimal courierFee() {
    return getProviderService().getByName(name()).get().getCourierFee();
  }

  public BigDecimal weatherFee() {
    var x= getPriceConfig().getWeatherFeeGlovo();
    return new BigDecimal(getPriceConfig().getWeatherFeeGlovo());
  }

  @Override
  public Price weight(BigDecimal weight, Price price) {
    return price;
  }
}
