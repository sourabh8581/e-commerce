package com.ecommerce.order.orderline;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderlineService {

  private final OrderLineRepository orderLineRepository;
  private final OrderLineMapper orderLineMapper;

  public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
    OrderLine orderLine = orderLineMapper.toOrderLine(orderLineRequest);
    return orderLineRepository.save(orderLine).getId();
  }

  public List<OrderLineResponse> findByOrderId(Integer orderId) {
    return orderLineRepository.findAllByOrderId(orderId)
        .stream()
        .map(orderLineMapper::fromOrderLine)
        .collect(Collectors.toList());
  }
}
