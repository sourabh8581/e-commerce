package com.ecommerce.order.order;

import com.ecommerce.order.customer.CustomerClient;
import com.ecommerce.order.order.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    public Integer createOrder(OrderRequest orderRequest) {
//        Check the customer --> Open Feign
        customerClient.findCustomerById(orderRequest.customerId()).orElseThrow(()->
                new BusinessException("Customer not exist with provided customer Id : "+orderRequest.customerId()));

//        purchase product -->> product microservice --> RestTemplate
        

//        persist order

//        persist order lines

//        start payment process

//        send order confirmation-->> kafka
    }
}
