package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.CustomerDTO;
import com.munaf.ERP_SYSTEM.entities.Customer;
import com.munaf.ERP_SYSTEM.exceptions.ResourceAlreadyExists;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.CustomerRepo;
import com.munaf.ERP_SYSTEM.services.CustomerService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceIMPL implements CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerServiceIMPL(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    @Override
    public ResponseModel getCustomerById(Long customerId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFound("Customer not found with id " + customerId));
        return CommonResponse.OK(CustomerDTO.customerToCustomerDto(customer));
    }

    @Override
    public ResponseModel getAllCustomer() {
        List<Customer> customers = customerRepo.findAll();
        List<CustomerDTO> customerDTOS = customers.stream()
                .map(customer -> CustomerDTO.customerToCustomerDto(customer))
                .toList();
        return CommonResponse.OK(customerDTOS);
    }

    @Override
    public ResponseModel createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerDTO.customerDtoToCustomer();
        Boolean isCustomerAlreadyExist = customerRepo.existsByEmailOrPhoneNo(customer.getEmail(), customer.getPhoneNo());
        if (isCustomerAlreadyExist)
            throw new ResourceAlreadyExists("Customer With This Email Or PhoneNumber already exists");

        Customer savedCustomer = customerRepo.save(customer);
        return CommonResponse.OK(CustomerDTO.customerToCustomerDto(savedCustomer));
    }

    @Override
    public ResponseModel updateCustomer(Long customerId, CustomerDTO updatedCustomer) {
        Customer customer = customerRepo.findById(customerId).orElseThrow(() -> new ResourceNotFound("Customer not found with id " + customerId));

        if (updatedCustomer.getCustomerName() != null) customer.setCustomerName(updatedCustomer.getCustomerName());
        if (updatedCustomer.getPhoneNo() != null) customer.setPhoneNo(updatedCustomer.getPhoneNo());
        if (updatedCustomer.getEmail() != null) customer.setEmail(updatedCustomer.getEmail());
        if (updatedCustomer.getAddress() != null) {
            if (updatedCustomer.getAddress().getArea() != null) {
                customer.getAddress().setArea(updatedCustomer.getAddress().getArea());
            }
            if (updatedCustomer.getAddress().getCity() != null) {
                customer.getAddress().setCity(updatedCustomer.getAddress().getCity());
            }
            if (updatedCustomer.getAddress().getState() != null) {
                customer.getAddress().setState(updatedCustomer.getAddress().getState());
            }
            if (updatedCustomer.getAddress().getPinCode() != null) {
                customer.getAddress().setPinCode(updatedCustomer.getAddress().getPinCode());
            }
        }

        Customer savedCustomer = customerRepo.save(customer);
        return CommonResponse.OK(CustomerDTO.customerToCustomerDto(savedCustomer));

    }

    @Override
    public ResponseModel deleteCustomer(Long customerId) {
        Boolean isCustomerExist = customerRepo.existsById(customerId);
        if (!isCustomerExist)
            throw new ResourceNotFound("Customer Not Exists With ID : " + customerId);

        customerRepo.deleteById(customerId);
        return CommonResponse.OK("CUSTOMER REMOVED");
    }


}
