package rs.edu.shipping.delivery.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProviderName {
  AKS("AKS"),
  BEX("BEX"),
  DEXPRESS("DEXPRESS"),
  GLOVO("GLOVO"),
  WOLT("WOLT");

  private final String value;

  @Override
  public String toString() {
    return value;
  }
}
