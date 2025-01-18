package com.munaf.ERP_SYSTEM.repositories;

import com.munaf.ERP_SYSTEM.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findByProductNameAndUserId(String pName, Long userId);

    Page<Product> findByUserId(Long userId, Pageable pageble);

    Optional<Product> findByIdAndUserId(Long userId, Long productId);
}
