package com.ecommerce.order.orderline;

public record OrderLineRequest(
    Integer id,
    Integer productId,
    Integer orderId,
    double quantity
) {

}
