package com.ecommerce.order.orderline;

import jakarta.ws.rs.Path;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {
  private final OrderlineService orderlineService;

  @GetMapping("/order/{order-id}")
  public ResponseEntity<List<OrderLineResponse>> findAllByOrderId(@PathVariable("order-id") Integer orderId){
    return ResponseEntity.ok(orderlineService.findByOrderId(orderId));
  }
}
