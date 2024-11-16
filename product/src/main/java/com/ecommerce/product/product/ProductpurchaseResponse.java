package com.ecommerce.product.product;

import java.math.BigDecimal;

public record ProductpurchaseResponse(
        Integer productId,
        String name,
        String description,
        BigDecimal price,
        double quantity
) {
}
