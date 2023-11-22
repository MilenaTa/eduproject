package rs.edu.shipping.delivery.display.data;

import java.math.BigDecimal;
import rs.edu.shipping.delivery.display.Display;
import rs.edu.shipping.delivery.display.ShippingData;

public class WoltDisplay implements Display {
  private String locationFrom;
  private String locationTo;
  private String cityFrom;
  private String cityTo;
  private BigDecimal weight;
  private String user;
  private ShippingData shippingData;

  public WoltDisplay(ShippingData shippingData) {
    this.shippingData = shippingData;
    shippingData.registerDisplay(this);
  }

  @Override
  public void update(String locationFrom, String locationTo, String cityFrom, String cityTo, BigDecimal weigth, String user) {
    this.locationFrom = locationFrom;
    this.locationTo = locationTo;
    this.cityFrom = cityFrom;
    this.cityTo = cityTo;
    this.weight = weigth;
    this.user = user;

    System.out.println("GLOVO DISPLAY FOR USER "+ user +"\n New shipping from " + locationFrom + ", " + cityFrom
        + " to " + locationTo + ", " + cityTo + ". Package weight: " + weight + " kg");
  }
}
