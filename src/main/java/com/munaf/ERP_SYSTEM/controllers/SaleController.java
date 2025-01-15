package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.services.SaleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/{userId}/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }
}
