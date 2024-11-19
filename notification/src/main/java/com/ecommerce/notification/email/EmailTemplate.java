package com.ecommerce.notification.email;

import lombok.Getter;

public enum EmailTemplate {
  PAYMENT_CONFIRMATION("payment-confirmation.html","Payment processed successfully"),
  ORDER_CONFIRMATION("order-confirmation.html","Payment processed successfully");

  @Getter
  private final String template;
  @Getter
  private final String subject;

  EmailTemplate(String subject, String template) {
    this.subject = subject;
    this.template = template;
  }
}
