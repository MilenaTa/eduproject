package rs.edu.shipping.delivery.service;

import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.edu.shipping.delivery.controller.request.CourierRequest;
import rs.edu.shipping.delivery.entity.CourierEntity;
import rs.edu.shipping.delivery.repository.CourierEntityRepository;
import rs.edu.shipping.user.constants.UserRole;
import rs.edu.shipping.user.service.UserService;

@Service
@RequiredArgsConstructor
public class CourierService {

  private final ProviderService providerService;
  private final CourierEntityRepository courierEntityRepository;
  private final UserService userService;

  public void create(CourierRequest request) {
    var user = userService.save(request.getUsername(), request.getPassword(), request.getFirstName(),
        request.getLastName(), UserRole.EMPLOYEE.getValue());
    CourierEntity courier = CourierEntity.builder()
        .user(user)
        .provider(providerService.getByName(request.getProvider()).get())
        .build();
    courierEntityRepository.save(courier);
  }

  public List<CourierEntity> getCouriersByProvider(String provider) {
    return courierEntityRepository.getCourierEntitiesByProvider(providerService.getByName(provider).get());
  }

  public Date getEarliestDeliveryDate(String provider) {
    return courierEntityRepository.findEarliestDeliveryDateByProvider(provider);
  }

  public CourierEntity get(Integer id) {
    return courierEntityRepository.getById(id);
  }


  public List<CourierEntity> getAvailableByProvider(String provider) {
    return courierEntityRepository.findAvailableByProvider(provider);
  }

  public CourierEntity getByUsername(String username){
    return courierEntityRepository.getCourierEntityByUser(userService.getByUsername(username));
  }

}
