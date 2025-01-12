package com.munaf.ERP_SYSTEM.entities.enums;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum InvoiceType {

    SALE("SALE"),
    PURCHASE("PURCHASE");

    private final String invoiceType;

    InvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }
}
