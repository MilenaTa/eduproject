package rs.edu.shipping.delivery.controller.request;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryRequest {
  private String locationFro;
  private String locationTo;
  private String cityFrom;
  private String cityTo;
  private BigDecimal weight;
}
