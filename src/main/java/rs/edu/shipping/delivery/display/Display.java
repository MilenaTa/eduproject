package rs.edu.shipping.delivery.display;

import java.math.BigDecimal;

public interface Display {
  void update(String locationFrom, String locationTo, String cityFrom, String cityTo, BigDecimal weigth, String user);
}
