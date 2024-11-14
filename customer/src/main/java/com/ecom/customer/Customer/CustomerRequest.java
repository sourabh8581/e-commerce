package com.ecom.customer.Customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,

         @NotNull(message = "First Name is required")
         String firstName,

         @NotNull(message = "Last Name is required")
         String lastName,

         @NotNull(message = "Email is required")
         @Email(message = "Must be a valid email")
         String email,

         Address address) {
}
