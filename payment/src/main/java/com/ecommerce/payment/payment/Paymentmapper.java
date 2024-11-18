package com.ecommerce.payment.payment;

import org.springframework.stereotype.Service;

@Service
public class Paymentmapper {
  public Payment toPayment(PaymentRequest paymentRequest){
    return Payment.builder()
        .id(paymentRequest.id())
        .amount(paymentRequest.amount())
        .orderId(paymentRequest.orderId())
        .paymentMethod(paymentRequest.paymentMethod())
        .build();
  }
}
