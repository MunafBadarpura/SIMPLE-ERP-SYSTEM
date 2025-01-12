package com.munaf.ERP_SYSTEM.repositories;

import com.munaf.ERP_SYSTEM.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Boolean existsByEmailOrPhoneNo(String email, String phoneNumber);
    Optional<User> findByEmail(String email);

}
