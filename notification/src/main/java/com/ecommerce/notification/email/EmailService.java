package com.ecommerce.notification.email;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender mailSender;
  private final SpringTemplateEngine templateEngine;

  @Async
  public void sendPaymentSuccessEmail(String destinationEmail,
      String customerName,
      BigDecimal amount,
      String orderReference){

  }
}
