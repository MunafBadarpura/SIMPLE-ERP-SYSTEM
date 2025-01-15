package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PurchaseService {

    ResponseModel purchaseProductFromSupplier(Long userId, Long supplierId, @RequestBody List<ProductDTO> productDTOS);

    ResponseModel getAllPurchases(Long userId);

    ResponseModel getPurchaseWithId(Long userId, Long purchaseId);
}
