package com.ecommerce.payment.payment;

import com.ecommerce.payment.notification.NotificationProducer;
import com.ecommerce.payment.notification.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

  private final PaymentRepository paymentRepository;
  private final Paymentmapper paymentmapper;
  private final NotificationProducer notificationProducer;

  public Integer createPayment(PaymentRequest paymentRequest) {
    Payment payment = paymentRepository.save(paymentmapper.toPayment(paymentRequest));
    notificationProducer.sendNotification(
        new PaymentNotificationRequest(
            paymentRequest.orderReference(),
            paymentRequest.amount(),
            paymentRequest.paymentMethod(),
            paymentRequest.customer().firstName(),
            paymentRequest.customer().lastName(),
            paymentRequest.customer().email()
        )
    );
    return payment.getId();

  }
}
