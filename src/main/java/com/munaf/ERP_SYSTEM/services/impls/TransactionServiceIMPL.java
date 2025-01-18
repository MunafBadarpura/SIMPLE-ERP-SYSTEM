package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.TransactionDTO;
import com.munaf.ERP_SYSTEM.entities.Transaction;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.TransactionService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceIMPL implements TransactionService {

    private final MasterRepo masterRepo;

    public TransactionServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    @Override
    @Transactional
    public ResponseModel getTransactions(Long userId, LocalDate dateFrom, LocalDate dateTo) {
        List<Transaction> transactions;

        if (dateFrom == null && dateTo == null) {
            transactions = masterRepo.getTransactionRepo().findByUserId(userId);
        }else {
            transactions = masterRepo.getTransactionRepo().findByTransactionDateBetween(dateFrom,dateTo);
        }

        List<TransactionDTO> transactionDTOS = transactions
                .stream().map(transaction -> TransactionDTO.transactionToTransactionDTO(transaction))
                .toList();

        return CommonResponse.OK(transactionDTOS);
    }
}
