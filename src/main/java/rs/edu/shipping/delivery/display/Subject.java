package rs.edu.shipping.delivery.display;

public interface Subject {
  public void registerDisplay(Display d);
  public void removeDisplay(Display d);
  public void notifyDisplay();
}
