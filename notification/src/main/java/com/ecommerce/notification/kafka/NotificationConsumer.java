package com.ecommerce.notification.kafka;

import com.ecommerce.notification.kafka.order.OrderConfirmation;
import com.ecommerce.notification.kafka.payment.PaymentConfirmation;
import com.ecommerce.notification.notification.Notification;
import com.ecommerce.notification.notification.NotificationRepository;
import com.ecommerce.notification.notification.NotificationType;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

  private final NotificationRepository notificationRepository;

  @KafkaListener(topics = "payment-topic")
  public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation){
    log.info("Consuming message from payment-topic :: %s",paymentConfirmation);
    notificationRepository.save(
        Notification.builder()
            .notificationType(NotificationType.PAYMENT_CONFIRMATION)
            .paymentConfirmation(paymentConfirmation)
            .notificationDate(LocalDateTime.now())
            .build()
    );

//    todo:Send email
  }

  @KafkaListener(topics = "order-topic")
  public void consumeOrderSuccessNotification(OrderConfirmation orderConfirmation){
    log.info("Consuming message from order-topic :: %s",orderConfirmation);
    notificationRepository.save(
        Notification.builder()
            .notificationType(NotificationType.ORDER_CONFIRMATION)
            .orderConfirmation(orderConfirmation)
            .notificationDate(LocalDateTime.now())
            .build()
    );

//    todo:Send email
  }
}
