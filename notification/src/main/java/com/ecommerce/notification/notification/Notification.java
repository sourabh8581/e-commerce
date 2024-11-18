package com.ecommerce.notification.notification;

import com.ecommerce.notification.kafka.order.OrderConfirmation;
import com.ecommerce.notification.kafka.payment.PaymentConfirmation;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Notification {
  @Id
  private String id;
  private NotificationType notificationType;
  private LocalDateTime notificationDate;
  private OrderConfirmation orderConfirmation;
  private PaymentConfirmation paymentConfirmation;
}
