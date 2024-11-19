package com.ecommerce.order.orderline;

import com.ecommerce.order.order.Order;
import com.ecommerce.order.order.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderlineService {

  private final OrderRepository orderRepository;
  private final OrderLineRepository orderLineRepository;
  private final OrderLineMapper orderLineMapper;

  public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
    Order order = orderRepository.findById(orderLineRequest.orderId()).orElseThrow(() ->
        new IllegalArgumentException(
            "Order not found with order id :" + orderLineRequest.orderId()));

    OrderLine orderLine = orderLineMapper.toOrderLine(orderLineRequest);
    orderLine.setOrder(order);
    return orderLineRepository.save(orderLine).getId();
  }

  public List<OrderLineResponse> findByOrderId(Integer orderId) {
    return orderLineRepository.findAllByOrderId(orderId)
        .stream()
        .map(orderLineMapper::fromOrderLine)
        .collect(Collectors.toList());
  }
}
