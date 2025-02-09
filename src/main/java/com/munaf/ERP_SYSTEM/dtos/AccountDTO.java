package com.munaf.ERP_SYSTEM.dtos;

import com.munaf.ERP_SYSTEM.entities.Account;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountDTO implements Serializable {

    private Long id;

    private Long accountBalance;

    private LocalDateTime lastUpdated;


    public static AccountDTO accountToAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAccountBalance(account.getAccountBalance());
        accountDTO.setLastUpdated(account.getLastUpdated());
        return accountDTO;
    }

}
