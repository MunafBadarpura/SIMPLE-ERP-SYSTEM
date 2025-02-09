package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.SupplierDTO;
import com.munaf.ERP_SYSTEM.services.SupplierService;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userId}/supplier")
@Tag(name = "SUPPLIER APIs", description = "With the help of this APIs user can add, update, remove supplier and manage them")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    // Get supplier by ID
    @GetMapping("/{supplierId}")
    public ResponseModel getSupplierById(@PathVariable Long userId, @PathVariable Long supplierId) {
        return supplierService.getSupplierById(userId, supplierId);
    }

    // Get all suppliers
    @GetMapping
    public PageResponseModel getAllSuppliers(@PathVariable Long userId,
                                             @RequestParam(defaultValue = "1") Integer pageNo,
                                             @RequestParam(defaultValue = "id") String sortBy) {
        return supplierService.getAllSuppliers(userId,pageNo,sortBy);
    }

    // Create a new supplier
    @PostMapping
    public ResponseModel createSupplier(@PathVariable Long userId, @RequestBody @Valid SupplierDTO supplierDTO) {
        return supplierService.createSupplier(userId, supplierDTO);
    }

    // Update supplier
    @PutMapping("/{supplierId}")
    public ResponseModel updateSupplier(@PathVariable Long userId, @PathVariable Long supplierId, @RequestBody @Valid SupplierDTO supplierDTO) {
        return supplierService.updateSupplier(userId, supplierId, supplierDTO);
    }

    // Delete supplier
    @DeleteMapping("/{supplierId}")
    public ResponseModel deleteSupplier(@PathVariable Long userId, @PathVariable Long supplierId) {
        return supplierService.deleteSupplier(userId, supplierId);
    }

}
