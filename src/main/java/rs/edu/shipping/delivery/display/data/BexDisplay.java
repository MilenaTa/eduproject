package rs.edu.shipping.delivery.display.data;

import java.math.BigDecimal;
import rs.edu.shipping.delivery.display.Display;
import rs.edu.shipping.delivery.display.ShippingData;

public class BexDisplay implements Display {

  private ShippingData shippingData;

  public BexDisplay(ShippingData shippingData) {
    this.shippingData = shippingData;
    shippingData.registerDisplay(this);
  }

  @Override
  public void update(String locationFrom, String locationTo, String cityFrom, String cityTo,
      BigDecimal weight, String user) {
    System.out.println(
        "BEX DISPLAY FOR USER " + user + " \n New shipping from " + locationFrom + ", " + cityFrom
            + " to " + locationTo + ", " + cityTo);
  }

}
