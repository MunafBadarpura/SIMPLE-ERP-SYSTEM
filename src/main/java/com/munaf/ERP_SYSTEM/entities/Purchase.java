package com.munaf.ERP_SYSTEM.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long purchaseAmount;

    private Long purchaseQuantity;

    @CreatedDate
    private LocalDateTime purchaseDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

//    @ManyToMany
//    @JoinTable(name = "purchase_product_table",
//            joinColumns = @JoinColumn(name = "purchase_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private List<Product> products;

    @OneToMany(mappedBy = "purchase")
    private List<Product> products;


}
