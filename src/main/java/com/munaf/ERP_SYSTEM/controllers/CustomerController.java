package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.CustomerDTO;
import com.munaf.ERP_SYSTEM.services.CustomerService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
//@RequestMapping("/user/{userId}/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // customer by id
    @GetMapping("/{customerId}")
    public ResponseModel getCustomerById(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }

    // see all customer
    @GetMapping
    public ResponseModel getAllCustomer() {
        return customerService.getAllCustomer();
    }

    // create customer
    @PostMapping
    public ResponseModel createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.createCustomer(customerDTO);
    }

    // update customer
    @PutMapping("/{customerId}")
    public ResponseModel updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerId, customerDTO);
    }

    // remove customer
    @DeleteMapping("/{customerId}")
    public ResponseModel deleteCustomer(@PathVariable Long customerId) {
        return customerService.deleteCustomer(customerId);
    }
}
