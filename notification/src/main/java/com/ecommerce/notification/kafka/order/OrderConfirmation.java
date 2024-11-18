package com.ecommerce.notification.kafka.order;

import com.ecommerce.notification.kafka.payment.PaymentMethod;
import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    List<Product> products,
    Customer customer
) {

}
