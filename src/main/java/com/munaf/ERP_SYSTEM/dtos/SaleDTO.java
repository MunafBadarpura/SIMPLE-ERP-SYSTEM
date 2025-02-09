package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Customer;
import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.Sale;
import com.munaf.ERP_SYSTEM.entities.User;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO implements Serializable {

    private Long id;

    private Long saleAmount;

    private Long saleQuantity;

    private LocalDateTime saleDate;

    private Long customerId;

    private List<Product> products;

    public static SaleDTO SaleToSaleDto(Sale sale) {
        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setId(sale.getId());
        saleDTO.setSaleAmount(sale.getSaleAmount());
        saleDTO.setSaleQuantity(sale.getSaleQuantity());
        saleDTO.setSaleDate(sale.getSaleDate());
        saleDTO.setCustomerId(sale.getCustomer().getId());
        saleDTO.setProducts(sale.getProducts());
        return saleDTO;
    }

}
