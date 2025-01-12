package com.munaf.ERP_SYSTEM.entities;

import com.munaf.ERP_SYSTEM.entities.enums.ExpenseType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

    private Long expenseAmount;

    private String description;

    @CreatedDate
    private LocalDateTime createdAt;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
}
