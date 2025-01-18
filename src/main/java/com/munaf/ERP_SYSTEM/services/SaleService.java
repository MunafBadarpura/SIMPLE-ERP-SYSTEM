package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.dtos.ProductDTO;
import com.munaf.ERP_SYSTEM.dtos.SaleProductDTO;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;

import java.util.List;

public interface SaleService {

    ResponseModel saleProductToCustomer(Long userId, Long customerId, List<SaleProductDTO> saleProductDTOS);

    PageResponseModel getAllSales(Long userId, Integer pageNo, String sortBy);

    ResponseModel getSaleById(Long userId, Long saleId);
}
