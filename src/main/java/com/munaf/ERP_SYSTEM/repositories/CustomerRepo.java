package com.munaf.ERP_SYSTEM.repositories;

import com.munaf.ERP_SYSTEM.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Boolean existsByEmailOrPhoneNo(String email, String phoneNumber);

}
