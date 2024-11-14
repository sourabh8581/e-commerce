package com.ecom.customer.Customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request){
        return ResponseEntity.ok(service.createCustomer(request));
    }
    @PutMapping
    public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest request){
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomers(){
        return ResponseEntity.ok(service.findAllCustomers());
    }
    @GetMapping("/exist/{customer-id}")
    public ResponseEntity<Boolean> customerExist(@PathVariable String customerId){
        return ResponseEntity.ok(service.existById(customerId));
    }
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findCustomer(@PathVariable String customerId){
        return ResponseEntity.ok(service.findById(customerId));
    }
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String customerId){
        service.deleteById(customerId);
        return ResponseEntity.accepted().build();
    }

}
