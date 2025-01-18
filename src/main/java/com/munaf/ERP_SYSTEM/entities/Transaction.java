package com.munaf.ERP_SYSTEM.entities;

import com.munaf.ERP_SYSTEM.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private Long transactionAmount;

    private Long accountBalance;

    @CreatedDate
    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Transaction() {
    }

    public Transaction(TransactionType transactionType, Long transactionAmount, Long accountBalance, Account account, User user) {
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.accountBalance = accountBalance;
        this.account = account;
        this.user = user;
    }
}
