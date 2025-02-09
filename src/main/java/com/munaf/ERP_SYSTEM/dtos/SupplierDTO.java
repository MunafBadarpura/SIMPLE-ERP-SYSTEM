package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Purchase;
import com.munaf.ERP_SYSTEM.entities.Sale;
import com.munaf.ERP_SYSTEM.entities.Supplier;
import com.munaf.ERP_SYSTEM.entities.User;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SupplierDTO implements Serializable {

    private Long id;

    @NotBlank(message = "supplier Name cannot be blank.")
    @Size(min = 3, max = 10, message = "supplier Name must be between 3 and 10 characters.")
    private String supplierName;

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

//    private List<Purchase> purchases;

    // Convert SupplierDTO to Supplier entity
    public Supplier supplierDtoToSupplier() {
        Supplier supplier = new Supplier();
        supplier.setSupplierName(this.getSupplierName());
        supplier.setAddress(this.getAddress());
        supplier.setEmail(this.getEmail());
        supplier.setPhoneNo(this.getPhoneNo());
        return supplier;
    }

    // Convert Supplier entity to SupplierDTO
    public static SupplierDTO supplierToSupplierDto(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(supplier.getId());
        supplierDTO.setSupplierName(supplier.getSupplierName());
        supplierDTO.setAddress(supplier.getAddress());
        supplierDTO.setEmail(supplier.getEmail());
        supplierDTO.setPhoneNo(supplier.getPhoneNo());
        supplierDTO.setCreatedAt(supplier.getCreatedAt());
        supplierDTO.setUpdatedAt(supplier.getUpdatedAt());
//        supplierDTO.setPurchases(supplier.getPurchases());
        return supplierDTO;
    }
}

