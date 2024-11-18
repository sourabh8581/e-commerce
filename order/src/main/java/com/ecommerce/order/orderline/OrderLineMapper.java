package com.ecommerce.order.orderline;

import com.ecommerce.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

  public OrderLine toOrderLine(OrderLineRequest orderLineRequest) {
    return OrderLine.builder()
        .id(orderLineRequest.id())
        .productId(orderLineRequest.productId())
        .quantity(orderLineRequest.quantity())
        .order(Order.builder()
            .id(orderLineRequest.id())
            .build())
        .build();

  }

  public OrderLineResponse fromOrderLine(OrderLine orderLine) {
    return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
  }
}
