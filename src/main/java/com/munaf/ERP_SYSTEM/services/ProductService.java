package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;

public interface ProductService {


    PageResponseModel getAllProducts(Long userId, Integer pageNo, String sortBy);

    ResponseModel getProductWithId(Long userId, Long productId);
}
