package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.services.TransactionService;
import com.munaf.ERP_SYSTEM.utils.PageResponseModel;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("user/{userId}/transactions")
@Tag(name = "TRANSACTION APIs", description = "With the help of this APIs user can see account transaction by date or all transactions ")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping()
    public ResponseModel getTransactions(@PathVariable Long userId,
                                             @RequestParam(required = false) LocalDate dateFrom,
                                             @RequestParam(required = false) LocalDate dateTo
                                             ) {
        return transactionService.getTransactions(userId, dateFrom, dateTo);
    }

}
