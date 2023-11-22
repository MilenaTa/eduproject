package rs.edu.shipping.delivery.domain;

import rs.edu.shipping.delivery.controller.request.DeliveryRequest;

public abstract class ProviderCreator {

  public Provider selectProvider(DeliveryRequest request, String name){
    Provider courier;

    courier = createCourier(name);

    courier.name = courier.name();
    courier.courierFee = courier.courierFee();
    courier.deliveryDate = courier.deliveryDate(request.getCityFrom(), request.getCityTo());
    courier.price = courier.price(request).add(courier.courierFee);

    return courier;
  }

  abstract Provider createCourier(String type);
}
