package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.SaleService;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceIMPL implements SaleService {

    private final MasterRepo masterRepo;

    public SaleServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }


}
