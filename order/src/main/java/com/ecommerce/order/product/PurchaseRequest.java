package com.ecommerce.order.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record PurchaseRequest(
        @NotNull(message = "Product is mandatory")
        @JsonProperty("productId")
        Integer productId,

        @NotNull(message = "Quantity is mandatory")
        @JsonProperty("quantity")
        double quantity

) {
}
