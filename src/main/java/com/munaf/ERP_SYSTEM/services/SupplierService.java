package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.dtos.SupplierDTO;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;

public interface SupplierService {
    ResponseModel getSupplierById(Long userId, Long supplierId);

    ResponseModel getAllSuppliers(Long userId);

    ResponseModel createSupplier(Long userId, SupplierDTO supplierDTO);

    ResponseModel updateSupplier(Long userId, Long supplierId, SupplierDTO supplierDTO);

    ResponseModel deleteSupplier(Long userId, Long supplierId);
}
