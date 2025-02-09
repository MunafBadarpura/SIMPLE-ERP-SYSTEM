package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.dtos.InvoiceDTO;
import com.munaf.ERP_SYSTEM.services.InvoiceService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/{userId}/invoice")
@Tag(name = "INVOICE APIs", description = "With the help of this APIs user can create and get invoices")
public class InvoiceController {
    // create invoice, get invoice by id, get all invoice

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseModel createInvoice(@PathVariable Long userId, @RequestBody @Valid InvoiceDTO invoiceDTO) {
        return invoiceService.createInvoice(userId, invoiceDTO);
    }

    @GetMapping("/{invoiceId}")
    public ResponseModel getInvoiceById(@PathVariable Long userId, @PathVariable Long invoiceId) {
        return invoiceService.getInvoiceById(userId, invoiceId);
    }

    @GetMapping
    public ResponseModel getAllInvoice(@PathVariable Long userId) {
        return invoiceService.getAllInvoice(userId);
    }
}
