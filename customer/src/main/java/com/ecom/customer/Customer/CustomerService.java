package com.ecom.customer.Customer;

import com.ecom.customer.Customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    public String createCustomer(CustomerRequest request) {

        Customer customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        Customer customer = customerRepository.findById(request.id()).orElseThrow(() ->
                new CustomerNotFoundException(
                        String.format("Cannot update customer :: No customer found with provided ID : %s " , request.id())
                ));
        mergeCustomer(customer,request);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setFirstName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setFirstName(request.email());
        }
        if(request.address()!=null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existById(String customerId) {
        return customerRepository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(CustomerMapper::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException(String.format("Customer not found with id : %s", customerId)));
    }

    public void deleteById(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
