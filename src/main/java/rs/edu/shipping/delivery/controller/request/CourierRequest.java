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
public class CourierRequest {
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String provider;
}
