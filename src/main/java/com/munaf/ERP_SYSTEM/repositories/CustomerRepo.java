package com.munaf.ERP_SYSTEM.repositories;

import com.munaf.ERP_SYSTEM.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Boolean existsByEmailOrPhoneNo(String email, String phoneNumber);

    Boolean existsByEmailAndUserId(String email, Long userId);
    Boolean existsByPhoneNoAndUserId(String phoneNo, Long userId);

    Optional<Customer> findByIdAndUserId(Long customerId, Long userId);

    List<Customer> findAllByUserId(Long userId);
    Page<Customer> findAllByUserId(Long userId, Pageable pageable);
}
