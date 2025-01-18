package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.CustomerDTO;
import com.munaf.ERP_SYSTEM.services.CustomerService;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userId}/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // customer by id
    @GetMapping("/{customerId}")
    public ResponseModel getCustomerById(@PathVariable Long userId,@PathVariable Long customerId) {
        return customerService.getCustomerById(userId,customerId);
    }

    // see all customer
    @GetMapping
    public PageResponseModel getAllCustomer(@PathVariable Long userId,
                                            @RequestParam(defaultValue = "1") Integer pageNo,
                                            @RequestParam(defaultValue = "id") String sortBy) {
        return customerService.getAllCustomer(userId, pageNo, sortBy);
    }

    // create customer
    @PostMapping
    public ResponseModel createCustomer(@PathVariable Long userId, @RequestBody @Valid CustomerDTO customerDTO) {
        return customerService.createCustomer(userId,customerDTO);
    }

    // update customer
    @PutMapping("/{customerId}")
    public ResponseModel updateCustomer(@PathVariable Long userId,@PathVariable Long customerId, @RequestBody @Valid CustomerDTO customerDTO) {
        return customerService.updateCustomer(userId,customerId, customerDTO);
    }

    // remove customer
    @DeleteMapping("/{customerId}")
    public ResponseModel deleteCustomer(@PathVariable Long userId,@PathVariable Long customerId) {
        return  customerService.deleteCustomer(userId,customerId);
    }
}
