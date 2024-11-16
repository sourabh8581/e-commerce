package com.ecommerce.order.order;

import com.ecommerce.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount shoould be positive")
        BigDecimal amount,
        @NotNull(message = "Payhment method should be precisized")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer should be present ")
        @NotEmpty(message = "Customer should be present ")
        @NotBlank(message = "Customer should be present ")
        String customerId,
        @NotEmpty(message = "You should purchase at least one product")
        List<PurchaseRequest> products
) {
}
