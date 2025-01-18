package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Customer;
import com.munaf.ERP_SYSTEM.entities.Sale;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerDTO {

    private Long id;

    @NotBlank(message = "Customer name cannot be blank.")
    @Size(min = 3, max = 10, message = "Customer name must be between 3 and 10 characters.")
    private String customerName;

    @Valid
    private Address address;

    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Please provide a valid email address.")
    private String email;

    @NotBlank(message = "Phone number cannot be blank.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits.")
    private String phoneNo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    private List<Sale> sales;

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
//        customerDTO.setSales(customer.getSales());
        return customerDTO;
    }

}
