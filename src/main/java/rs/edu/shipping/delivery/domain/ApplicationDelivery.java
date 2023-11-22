package rs.edu.shipping.delivery.domain;

import java.util.List;
import rs.edu.shipping.delivery.constants.ProviderName;
import rs.edu.shipping.delivery.domain.applications.ApplicationsAdapter;
import rs.edu.shipping.delivery.domain.applications.Glovo;
import rs.edu.shipping.delivery.domain.applications.Wolt;

public class ApplicationDelivery extends ProviderCreator {

  @Override
  public Provider createCourier(String type) {
    if(type.equals(ProviderName.GLOVO.getValue())){
      return new ApplicationsAdapter(new Glovo());
    } else if (type.equals(ProviderName.WOLT.getValue())) {
      return new ApplicationsAdapter(new Wolt());
    } else return null;
  }

}
