package com.ecom.customer.Customer.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String,String> errors
) {
}
