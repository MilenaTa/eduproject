package rs.edu.shipping.delivery.controller.request;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OfferRequest {
  private String locationFrom;
  private String locationTo;
  private String cityFrom;
  private String cityTo;
  private BigDecimal weight;
}
