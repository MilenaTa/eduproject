package rs.edu.shipping.delivery.domain.applications;

import java.math.BigDecimal;
import rs.edu.shipping.delivery.constants.ProviderName;
import rs.edu.shipping.delivery.domain.Provider;
import rs.edu.shipping.delivery.price.Price;

public class Wolt extends Provider {

  public String name() {
    return ProviderName.WOLT.getValue();
  }

  public BigDecimal courierFee() {
    return getProviderService().getByName(name()).get().getCourierFee();
  }

  public BigDecimal weatherFee() {
    return new BigDecimal(getPriceConfig().getWeatherFeeWolt());
  }

  @Override
  public Price weight(BigDecimal weight, Price price) {
    return price;
  }
}
