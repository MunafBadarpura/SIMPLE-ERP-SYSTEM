package com.munaf.ERP_SYSTEM.repositories;

import com.munaf.ERP_SYSTEM.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepo extends JpaRepository<Sale, Long> {


    List<Sale> findByUserId(Long userId);

    Optional<Sale> findByIdAndUserId(Long saleId, Long userId);
}
