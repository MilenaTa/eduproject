package rs.edu.shipping.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@Getter
@Setter
public class DeliveryTimeConfig {

  @Value("${deliveryTime.aks.default}")
  private Integer defaultAks;
  @Value("${deliveryTime.aks.delay}")
  private Integer delayAks;

  @Value("${deliveryTime.bex.defaultCity}")
  private Integer defaultCityBex;
  @Value("${deliveryTime.bex.defaultIntercity}")
  private Integer defaultIntercityBex;
  @Value("${deliveryTime.bex.delayCity}")
  private Integer delayCityBex;
  @Value("${deliveryTime.bex.delayIntercity}")
  private Integer delayIntercityBex;

  @Value("${deliveryTime.dExpress.defaultCity}")
  private Integer defaultCityDexpress;
  @Value("${deliveryTime.dExpress.defaultIntercity}")
  private Integer defaultIntercityDexpress;
  @Value("${deliveryTime.dExpress.delayCity}")
  private Integer delayCityDexpress;
  @Value("${deliveryTime.dExpress.delayIntercity}")
  private Integer delayIntercityDexpress;
  @Value("${deliveryTime.dExpress.moreDelayCity}")
  private Integer moreDelayCityDexpress;
  @Value("${deliveryTime.dExpress.moreDelayIntercity}")
  private Integer moreDelayIntercityDexpress;
}
