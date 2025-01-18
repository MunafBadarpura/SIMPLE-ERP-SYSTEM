package com.munaf.ERP_SYSTEM.repositories;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class MasterRepo {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private SupplierRepo supplierRepo;
    @Autowired
    private PurchaseRepo purchaseRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private SaleRepo saleRepo;
    @Autowired
    private AccountRepo accountRepo;
    @Autowired
    private TransactionRepo transactionRepo;
}

