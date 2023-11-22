package rs.edu.shipping.delivery.controller;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.edu.shipping.delivery.entity.ProviderEntity;
import rs.edu.shipping.delivery.service.ProviderService;

@RestController
@RequestMapping(value = "/provider")
@RequiredArgsConstructor
public class ProviderController {

  private final ProviderService providerService;

  @PostMapping(value = "/update")
  public ResponseEntity<Void> update(String name, BigDecimal courierFee){
    try {
      providerService.update(name, courierFee);
      return ResponseEntity.ok().build();
    } catch (Exception e){
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<Void> create(String name, BigDecimal courierFee){
    try {
      providerService.create(name, courierFee);
      return ResponseEntity.ok().build();
    } catch (Exception e){
      return ResponseEntity.internalServerError().build();
    }
  }

  @GetMapping
  public ResponseEntity<ProviderEntity> getByName(String name){
    System.out.println("Provider Controller: jel uspelo?");
    return ResponseEntity.of(providerService.getByName(name));
  }
}
