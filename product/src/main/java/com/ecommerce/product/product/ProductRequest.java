package com.ecommerce.product.product;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
         Integer id,
         @NotNull(message = "Product name is required")
         String name,
         @NotNull(message = "Product description is required")
         String description,
         @NotNull(message = "Available quantity is required")
         Double availableQuantity,
         @NotNull(message = "Product price is required")
         BigDecimal price,
         @NotNull(message = "Product category is required")
         Integer categoryId
) {
}
