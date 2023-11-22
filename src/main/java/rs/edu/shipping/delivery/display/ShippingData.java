package rs.edu.shipping.delivery.display;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShippingData implements Subject {
  private String locationFrom;
  private String locationTo;
  private String cityFrom;
  private String cityTo;
  private BigDecimal weight;
  private String user;
  private List<Display> displays;

  public ShippingData() {
    displays = new ArrayList<Display>();
  }

  @Override
  public void registerDisplay(Display d) {
    displays.add(d);
  }

  @Override
  public void removeDisplay(Display d) {
    displays.remove(d);
  }

  @Override
  public void notifyDisplay() {
    for(Display d:displays){
      d.update(locationFrom, locationTo, cityFrom, cityTo, weight, user);
    }
  }

  public void setData(String locationFrom, String locationTo, String cityFrom, String cityTo, BigDecimal weigth, String user){
    this.locationFrom = locationFrom;
    this.locationTo = locationTo;
    this.cityFrom = cityFrom;
    this.cityTo = cityTo;
    this.weight = weigth;
    this.user = user;

    notifyDisplay();
  }

}
