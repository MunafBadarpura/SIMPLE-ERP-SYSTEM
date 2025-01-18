package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.services.PurchaseService;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/{userId}/purchase")
@Validated
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("supplier/{supplierId}")
    public ResponseModel purchaseProductFromSupplier(@PathVariable Long userId,@PathVariable Long supplierId, @Valid @RequestBody List<ProductDTO> productDTOS) {
        return purchaseService.purchaseProductFromSupplier(userId, supplierId, productDTOS);
    }


    // GET ALL PURCHASES
    @GetMapping
    public PageResponseModel getAllPurchases(@PathVariable Long userId,
                                             @RequestParam(defaultValue = "1") Integer pageNo,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return purchaseService.getAllPurchases(userId,pageNo,sortBy);
    }

    // GET PURCHASE WITH ID
    @GetMapping("/{purchaseId}")
    public ResponseModel getPurchaseWithId(@PathVariable Long userId, @PathVariable Long purchaseId) {
        return purchaseService.getPurchaseWithId(userId, purchaseId);
    }

}
