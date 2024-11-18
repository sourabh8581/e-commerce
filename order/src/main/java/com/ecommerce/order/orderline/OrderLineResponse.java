package com.ecommerce.order.orderline;

public record OrderLineResponse(
    Integer orderId,
    double quantity
) {

}
