package com.ecommerce.product.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(
    @NotNull(message = "Product is mandatory")
    Integer productId,

    @NotNull(message = "Quantity is mandatory")
    double quantity
) {
}
