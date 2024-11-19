package com.ecommerce.order.order;

import com.ecommerce.order.customer.CustomerClient;
import com.ecommerce.order.customer.CustomerResponse;
import com.ecommerce.order.kafka.OrderConfirmation;
import com.ecommerce.order.kafka.OrderProducer;
import com.ecommerce.order.order.exception.BusinessException;
import com.ecommerce.order.orderline.OrderLineRequest;
import com.ecommerce.order.orderline.OrderlineService;
import com.ecommerce.order.payment.PaymentClient;
import com.ecommerce.order.payment.PaymentRequest;
import com.ecommerce.order.product.ProductClient;
import com.ecommerce.order.product.PurchaseRequest;
import com.ecommerce.order.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final CustomerClient customerClient;
  private final ProductClient productClient;
  private final OrderMapper orderMapper;
  private final OrderlineService orderlineService;
  private final OrderProducer orderProducer;
  private final PaymentClient paymentClient;

  public Integer createOrder(OrderRequest orderRequest) {
//        Check the customer --> Open Feign
    CustomerResponse customer = this.customerClient.findCustomerById(
        orderRequest.customerId()).orElseThrow(() -> new BusinessException(
        "Customer not exist with provided customer Id : " + orderRequest.customerId()));

//        purchase product -->> product microservice --> RestTemplate
    List<PurchaseResponse> purchasedProducts = this.productClient.purchaseProducts(
        orderRequest.products());

//        persist order
    Order order = this.orderRepository.save(orderMapper.toOrder(orderRequest));

//        persist order lines
    for (PurchaseRequest purchaseRequest : orderRequest.products()) {
      OrderLineRequest orderLineRequest = new OrderLineRequest(
          null,
          order.getId(),
          purchaseRequest.productId(),
          purchaseRequest.quantity()
      );
      orderlineService.saveOrderLine(orderLineRequest);
    }
//        todo:start payment process
    PaymentRequest paymentRequest = new PaymentRequest(orderRequest.amount(),
        orderRequest.paymentMethod(),
        orderRequest.id(),
        orderRequest.reference(),
        customer);

    paymentClient.requestOrderPayment(paymentRequest);

//        send order confirmation-->> kafka
    orderProducer.sendOrderConfirmation(
        new OrderConfirmation(
            orderRequest.reference(),
            orderRequest.amount(),
            orderRequest.paymentMethod(),
            customer,
            purchasedProducts
        )
    );
    return order.getId();
  }

  public List<OrderResponse> findAll() {
     return orderRepository.findAll()
         .stream().map(orderMapper::fromOrder)
         .collect(Collectors.toList());
  }

  public OrderResponse findById(Integer orderId) {
    return orderRepository.findById(orderId)
        .map(orderMapper::fromOrder)
        .orElseThrow(()-> new EntityNotFoundException("Order not found with id : "+ orderId));
  }
}
