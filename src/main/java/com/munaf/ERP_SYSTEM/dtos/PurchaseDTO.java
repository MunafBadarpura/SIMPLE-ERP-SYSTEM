package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.Purchase;
import com.munaf.ERP_SYSTEM.entities.Supplier;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PurchaseDTO {

    private Long id;

    private Long purchaseAmount;

    private Long purchaseQuantity;

    private LocalDateTime purchaseDate;

    private Supplier supplier;

    private List<Product> products;



    public static PurchaseDTO purchaseToPurchaseDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(purchase.getId());
        purchaseDTO.setPurchaseAmount(purchase.getPurchaseAmount());
        purchaseDTO.setPurchaseQuantity(purchase.getPurchaseQuantity());
        purchaseDTO.setPurchaseDate(purchase.getPurchaseDate());
        purchaseDTO.setSupplier(purchase.getSupplier());
        purchaseDTO.setProducts(purchase.getProducts());
        return purchaseDTO;
    }

}
