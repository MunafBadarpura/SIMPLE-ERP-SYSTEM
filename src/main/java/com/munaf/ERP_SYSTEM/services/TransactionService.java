package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.utils.ResponseModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TransactionService {
    ResponseModel getTransactions(Long userId, LocalDate dateFrom, LocalDate dateTo);
}
