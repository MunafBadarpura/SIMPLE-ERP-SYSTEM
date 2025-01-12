package com.munaf.ERP_SYSTEM.entities.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ExpenseType {

    SALARIES("SALARIES"),
    RENT("RENT"),
    OTHER("OTHER");

    private final String expenseType;

    ExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }
}
