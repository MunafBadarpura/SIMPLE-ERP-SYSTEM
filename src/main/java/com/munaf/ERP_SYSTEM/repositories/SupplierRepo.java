package com.munaf.ERP_SYSTEM.repositories;

import com.munaf.ERP_SYSTEM.entities.Customer;
import com.munaf.ERP_SYSTEM.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {


    Boolean existsByEmailAndUserId(String email, Long userId);
    Boolean existsByPhoneNoAndUserId(String phoneNo, Long userId);

    Optional<Supplier> findByIdAndUserId(Long customerId, Long userId);

    List<Supplier> findAllByUserId(Long supplierId);

}