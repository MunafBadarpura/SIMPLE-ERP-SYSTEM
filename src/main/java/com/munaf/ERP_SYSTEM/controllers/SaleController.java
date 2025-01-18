package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.SaleProductDTO;
import com.munaf.ERP_SYSTEM.services.SaleService;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/{userId}/sale")
@Validated
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/customer/{customerId}")
    public ResponseModel saleProductToCustomer(@PathVariable Long userId, @PathVariable Long customerId, @RequestBody @Valid List< SaleProductDTO> saleProductDTOS) {
        return saleService.saleProductToCustomer(userId, customerId, saleProductDTOS);
    }


    @GetMapping
    public PageResponseModel getAllSales(@PathVariable Long userId,
                                         @RequestParam(defaultValue = "1") Integer pageNo,
                                         @RequestParam(defaultValue = "id") String sortBy) {
        return saleService.getAllSales(userId, pageNo, sortBy);
    }

    @GetMapping("/{saleId}")
    public ResponseModel getSaleById(@PathVariable Long userId, @PathVariable Long saleId) {
        return saleService.getSaleById(userId, saleId);
    }
}
