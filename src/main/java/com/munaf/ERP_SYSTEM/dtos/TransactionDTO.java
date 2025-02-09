package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Transaction;
import com.munaf.ERP_SYSTEM.entities.enums.TransactionType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TransactionDTO  implements Serializable {

    private Long id;

    private TransactionType transactionType;

    private Long transactionAmount;

    private Long accountBalance;

    private LocalDate transactionDate;


    public static TransactionDTO transactionToTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setTransactionType(transaction.getTransactionType());
        transactionDTO.setTransactionAmount(transaction.getTransactionAmount());
        transactionDTO.setAccountBalance(transaction.getAccountBalance());
        transactionDTO.setTransactionDate(transaction.getTransactionDate());

        return transactionDTO;
    }
}
