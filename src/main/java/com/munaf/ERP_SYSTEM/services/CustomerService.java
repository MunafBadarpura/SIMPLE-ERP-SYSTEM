package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.dtos.CustomerDTO;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;

public interface CustomerService {
    ResponseModel createCustomer(CustomerDTO customerDTO);

    ResponseModel updateCustomer(Long customerId, CustomerDTO customerDTO);

    ResponseModel deleteCustomer(Long customerId);

    ResponseModel getCustomerById(Long customerId);

    ResponseModel getAllCustomer();
}
