package rs.edu.shipping.user.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRole {
  ADMIN("ADMIN"),
  EMPLOYEE("EMPLOYEE"),
  CUSTOMER("CUSTOMER");
  private final String value;

  @Override
  public String toString() {
    return value;
  }
}
