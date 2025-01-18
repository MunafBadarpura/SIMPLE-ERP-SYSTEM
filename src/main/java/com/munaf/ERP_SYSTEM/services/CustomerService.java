package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.dtos.CustomerDTO;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;

public interface CustomerService {


    ResponseModel getCustomerById(Long userId, Long customerId);

    PageResponseModel getAllCustomer(Long userId, Integer pageNo, String sortBy);

    ResponseModel createCustomer(Long userId, CustomerDTO customerDTO);

    ResponseModel updateCustomer(Long userId, Long customerId, CustomerDTO customerDTO);

    ResponseModel deleteCustomer(Long userId, Long customerId);
}
