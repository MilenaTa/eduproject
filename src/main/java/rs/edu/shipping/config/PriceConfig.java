package rs.edu.shipping.config;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.yml")
@Getter
@Setter
public class PriceConfig {

  @Value("${price.weightFeeAmount}")
  private BigDecimal weightFeeAmount;
  @Value("${price.weightFeeDescription}")
  private String weightFeeDescription;
  @Value("${price.cityShippingAmount}")
  private BigDecimal cityShippingAmount;
  @Value("${price.cityShippingDescription}")
  private String cityShippingDescription;
  @Value("${price.intercityShippingAmount}")
  private BigDecimal intercityShippingAmount;
  @Value("${price.intercityShippingDescription}")
  private String intercityShippingDescription;
  @Value("${price.weatherFeeGlovo}")
  private String weatherFeeGlovo;
  @Value("${price.weatherFeeWolt}")
  private String weatherFeeWolt;
  @Value("${price.weightLimitAks}")
  private BigDecimal weightLimitAks;
  @Value("${price.weightLimitDexpress}")
  private BigDecimal weightLimitDexpress;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
