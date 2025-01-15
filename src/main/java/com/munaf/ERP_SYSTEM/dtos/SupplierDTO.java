package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Purchase;
import com.munaf.ERP_SYSTEM.entities.Sale;
import com.munaf.ERP_SYSTEM.entities.Supplier;
import com.munaf.ERP_SYSTEM.entities.User;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SupplierDTO {

    private Long id;


    private String supplierName;

    private Address address;

    private String email;

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

