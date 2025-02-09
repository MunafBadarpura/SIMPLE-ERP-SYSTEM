package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Product;
import com.munaf.ERP_SYSTEM.entities.Purchase;
import com.munaf.ERP_SYSTEM.entities.Supplier;
import jakarta.validation.Valid;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PurchaseDTO implements Serializable {

    private Long id;

    private Long purchaseAmount;

    private Long purchaseQuantity;

    private LocalDateTime purchaseDate;

    private Long supplierId;

    private List<Product> products;



    public static PurchaseDTO purchaseToPurchaseDTO(Purchase purchase) {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setId(purchase.getId());
        purchaseDTO.setPurchaseAmount(purchase.getPurchaseAmount());
        purchaseDTO.setPurchaseQuantity(purchase.getPurchaseQuantity());
        purchaseDTO.setPurchaseDate(purchase.getPurchaseDate());
        purchaseDTO.setSupplierId(purchase.getSupplier().getId());
        purchaseDTO.setProducts(purchase.getProducts());

        return purchaseDTO;
    }

}
