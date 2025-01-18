package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;

import java.util.List;

public interface PurchaseService {

    ResponseModel purchaseProductFromSupplier(Long userId, Long supplierId,List<ProductDTO> productDTOS);

    PageResponseModel getAllPurchases(Long userId, Integer pageNo, String sortBy);

    ResponseModel getPurchaseWithId(Long userId, Long purchaseId);
}
