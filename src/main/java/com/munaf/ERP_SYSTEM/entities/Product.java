package com.munaf.ERP_SYSTEM.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.munaf.ERP_SYSTEM.entities.enums.Category;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long productStock;

    private Long productPurchasePrice;

    private Long productSalePrice;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Sale> sales = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private List<Purchase> purchases = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "purchase_id")
//    @JsonIgnore
//    private Purchase purchase;
}
