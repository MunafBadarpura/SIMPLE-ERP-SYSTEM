package com.munaf.ERP_SYSTEM.entities;

import com.munaf.ERP_SYSTEM.dtos.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable , UserDetails{ // Spring Security requires a UserDetails object to authenticate and authorize users.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String phoneNo;

    private String email;

    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Embedded
    private Address address;


    @OneToOne(mappedBy = "user")
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<Customer> customers;

    @OneToMany(mappedBy = "user")
    private List<Supplier> suppliers;

    @OneToMany(mappedBy = "user")
    private List<Product> products;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;

    @OneToMany(mappedBy = "user")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "user")
    private List<Invoice> invoices;

    // UserDetails interface manditory method
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
