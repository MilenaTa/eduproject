package rs.edu.shipping.delivery.domain;

import java.util.List;
import rs.edu.shipping.delivery.constants.ProviderName;
import rs.edu.shipping.delivery.domain.courier.Aks;
import rs.edu.shipping.delivery.domain.courier.Bex;
import rs.edu.shipping.delivery.domain.courier.Dexpress;

public class CourierDelivery extends ProviderCreator {

  @Override
  Provider createCourier(String type) {
    if(type.equals(ProviderName.AKS.getValue())){
      return new Aks();
    } else if (type.equals(ProviderName.BEX.getValue())) {
      return new Bex();
    } else if (type.equals(ProviderName.DEXPRESS.getValue())) {
      return new Dexpress();
    } else return null;
  }

}
