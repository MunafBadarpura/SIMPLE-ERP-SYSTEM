package com.munaf.ERP_SYSTEM.entities.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum TransactionType {

    CREDIT("CREDIT"),
    DEBIT("DEBIT");

    private final String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
