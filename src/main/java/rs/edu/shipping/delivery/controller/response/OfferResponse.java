package rs.edu.shipping.delivery.controller.response;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OfferResponse {
  private String provider;
  private BigDecimal price;
  private Date deliveryDate;
}
