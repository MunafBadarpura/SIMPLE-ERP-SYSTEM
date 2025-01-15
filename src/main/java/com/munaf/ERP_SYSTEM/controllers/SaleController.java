package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.dtos.SaleProductDTO;
import com.munaf.ERP_SYSTEM.services.SaleService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/{userId}/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/customer/{customerId}")
    public ResponseModel saleProductToCustomer(@PathVariable Long userId, @PathVariable Long customerId, @RequestBody List<SaleProductDTO> saleProductDTOS) {
        return saleService.saleProductToCustomer(userId, customerId, saleProductDTOS);
    }


    @GetMapping
    public ResponseModel getAllSales(@PathVariable Long userId) {
        return saleService.getAllSales(userId);
    }

    @GetMapping("/{saleId}")
    public ResponseModel getSaleById(@PathVariable Long userId, @PathVariable Long saleId) {
        return saleService.getSaleById(userId, saleId);
    }
}
