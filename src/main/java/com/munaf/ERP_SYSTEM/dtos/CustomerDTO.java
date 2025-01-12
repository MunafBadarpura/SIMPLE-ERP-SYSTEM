package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Customer;
import com.munaf.ERP_SYSTEM.entities.Sale;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerDTO {

    private Long id;

    private String customerName;

    private Address address;

    private String email;

    private String phoneNo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<Sale> sales;

    public Customer customerDtoToCustomer(){
        Customer customer = new Customer();
        customer.setCustomerName(this.getCustomerName());
        customer.setAddress(this.getAddress());
        customer.setEmail(this.getEmail());
        customer.setPhoneNo(this.getPhoneNo());

        return customer;
    }

    public static CustomerDTO customerToCustomerDto(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setCustomerName(customer.getCustomerName());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhoneNo(customer.getPhoneNo());
        customerDTO.setCreatedAt(customer.getCreatedAt());
        customerDTO.setUpdatedAt(customer.getUpdatedAt());
        customerDTO.setSales(customer.getSales());
        return customerDTO;
    }

}
