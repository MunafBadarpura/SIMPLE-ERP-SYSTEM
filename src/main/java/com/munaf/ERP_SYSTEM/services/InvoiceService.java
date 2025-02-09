package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.dtos.InvoiceDTO;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import jakarta.validation.Valid;

public interface InvoiceService {
    ResponseModel createInvoice(Long userId, @Valid InvoiceDTO invoiceDTO);

    ResponseModel getInvoiceById(Long userId, Long invoiceId);

    ResponseModel getAllInvoice(Long userId);
}
