package com.munaf.ERP_SYSTEM.repositories;

import com.munaf.ERP_SYSTEM.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepo extends JpaRepository<Purchase, Long> {

    List<Purchase> findByUserId(Long userId);

    Optional<Purchase> findByIdAndUserId(Long purchaseId, Long userId);
}
