package rs.edu.shipping.delivery.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.edu.shipping.delivery.controller.request.CourierRequest;
import rs.edu.shipping.delivery.entity.CourierEntity;
import rs.edu.shipping.delivery.service.CourierService;

@RestController
@RequestMapping(value = "/courier")
@RequiredArgsConstructor
public class CourierController {
  private final CourierService courierService;

  @PostMapping(value = "/create")
  public ResponseEntity<Void> create(CourierRequest request){
    courierService.create(request);
    return ResponseEntity.ok().build();
  }

  @GetMapping(value = "/available")
  public List<CourierEntity> getAvailableByProvider(String provider) {
    return courierService.getAvailableByProvider(provider);
  }

}
