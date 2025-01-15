package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.utils.ResponseModel;

public interface ProductService {


    ResponseModel getAllProducts(Long userId);

    ResponseModel getProductWithId(Long userId, Long productId);
}
