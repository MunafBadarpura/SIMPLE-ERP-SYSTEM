package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Invoice;
import com.munaf.ERP_SYSTEM.entities.enums.InvoiceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO implements Serializable {

    private Long id;

    private InvoiceType invoiceType;

    private Long invoiceAmount;

    private LocalDateTime createdAt;


    public Invoice invoiceDtoToInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceType(this.getInvoiceType());
        invoice.setInvoiceAmount(this.getInvoiceAmount());

        return invoice;
    }

    public static InvoiceDTO invoiceToInvoiceDTO(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setInvoiceType(invoice.getInvoiceType());
        invoiceDTO.setInvoiceAmount(invoice.getInvoiceAmount());
        invoiceDTO.setCreatedAt(invoice.getCreatedAt());

        return invoiceDTO;
    }

}
