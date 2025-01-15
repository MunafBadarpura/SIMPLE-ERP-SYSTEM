package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Customer;
import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.Sale;
import com.munaf.ERP_SYSTEM.entities.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO {

    private Long id;

    private Long saleAmount;

    private Long saleQuantity;

    private LocalDateTime saleDate;

    private Customer customer;

    private List<Product> products;

    public static SaleDTO SaleToSaleDto(Sale sale) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(sale.getId());
        saleDTO.setSaleAmount(sale.getSaleAmount());
        saleDTO.setSaleQuantity(sale.getSaleQuantity());
        saleDTO.setSaleDate(sale.getSaleDate());
        saleDTO.setCustomer(sale.getCustomer());
        saleDTO.setProducts(sale.getProducts());
        return saleDTO;
    }

}
