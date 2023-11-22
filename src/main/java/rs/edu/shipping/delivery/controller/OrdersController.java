package rs.edu.shipping.delivery.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.edu.shipping.delivery.controller.request.OfferRequest;
import rs.edu.shipping.delivery.controller.request.OrderRequest;
import rs.edu.shipping.delivery.controller.response.OfferResponse;
import rs.edu.shipping.delivery.service.OrdersService;

@RestController
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrdersController {
  private final OrdersService ordersService;


  @GetMapping(value = "/request")
  public List<OfferResponse> requestOffer(OfferRequest request){
    try {
      return ordersService.requestOffer(request);
    } catch (Exception e){
      throw e;
    }
  }

  @PostMapping(value = "/create")
  public ResponseEntity<Void> create(OrderRequest request){
    try {
      ordersService.create(request);
      return ResponseEntity.ok().build();
    } catch (Exception e){
      return ResponseEntity.internalServerError().build();
    }
  }

  @PostMapping(value = "/choose")
  public ResponseEntity<Void> chooseOrder(Integer orderId){
    try {
      ordersService.chooseOrder(orderId);
      return ResponseEntity.ok().build();
    } catch (Exception e){
      return ResponseEntity.internalServerError().build();
    }
  }

}
