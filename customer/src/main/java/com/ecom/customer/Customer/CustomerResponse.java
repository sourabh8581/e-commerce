package com.ecom.customer.Customer;

public record CustomerResponse(
        String id,
         String firstName,
         String lastName,
         String email,
         Address address) {

}