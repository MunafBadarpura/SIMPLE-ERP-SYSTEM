package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.SupplierDTO;
import com.munaf.ERP_SYSTEM.services.SupplierService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{userId}/supplier")
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
    public ResponseModel getAllSuppliers(@PathVariable Long userId) {
        return supplierService.getAllSuppliers(userId);
    }

    // Create a new supplier
    @PostMapping
    public ResponseModel createSupplier(@PathVariable Long userId, @RequestBody SupplierDTO supplierDTO) {
        return supplierService.createSupplier(userId, supplierDTO);
    }

    // Update supplier
    @PutMapping("/{supplierId}")
    public ResponseModel updateSupplier(@PathVariable Long userId, @PathVariable Long supplierId, @RequestBody SupplierDTO supplierDTO) {
        return supplierService.updateSupplier(userId, supplierId, supplierDTO);
    }

    // Delete supplier
    @DeleteMapping("/{supplierId}")
    public ResponseModel deleteSupplier(@PathVariable Long userId, @PathVariable Long supplierId) {
        return supplierService.deleteSupplier(userId, supplierId);
    }

}
