package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.services.PurchaseService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/{userId}/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("supplier/{supplierId}")
    public ResponseModel purchaseProductFromSupplier(@PathVariable Long userId,@PathVariable Long supplierId, @RequestBody List<ProductDTO> productDTOS) {
        return purchaseService.purchaseProductFromSupplier(userId, supplierId, productDTOS);
    }


    // GET ALL PURCHASES
    @GetMapping
    public ResponseModel getAllPurchases(@PathVariable Long userId) {
        return purchaseService.getAllPurchases(userId);
    }

    // GET PURCHASE WITH ID
    @GetMapping("/{purchaseId}")
    public ResponseModel getPurchaseWithId(@PathVariable Long userId, @PathVariable Long purchaseId) {
        return purchaseService.getPurchaseWithId(userId, purchaseId);
    }

}
