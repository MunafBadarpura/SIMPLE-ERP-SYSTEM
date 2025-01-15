package com.munaf.ERP_SYSTEM.repositories;

import com.munaf.ERP_SYSTEM.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findByProductNameAndUserId(String pName, Long userId);

    List<Product> findByUserId(Long userId);

    Optional<Product> findByIdAndUserId(Long userId, Long productId);
}
