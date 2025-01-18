package com.munaf.ERP_SYSTEM.services.impls;

import com.munaf.ERP_SYSTEM.dtos.AccountDTO;
import com.munaf.ERP_SYSTEM.entities.Account;
import com.munaf.ERP_SYSTEM.entities.Transaction;
import com.munaf.ERP_SYSTEM.entities.User;
import com.munaf.ERP_SYSTEM.entities.enums.TransactionType;
import com.munaf.ERP_SYSTEM.exceptions.InvalidInputException;
import com.munaf.ERP_SYSTEM.exceptions.ResourceNotFound;
import com.munaf.ERP_SYSTEM.repositories.MasterRepo;
import com.munaf.ERP_SYSTEM.services.AccountService;
import com.munaf.ERP_SYSTEM.utils.CommonResponse;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountServiceIMPL implements AccountService {

    private final MasterRepo masterRepo;

    public AccountServiceIMPL(MasterRepo masterRepo) {
        this.masterRepo = masterRepo;
    }

    public User getUserWithId(Long userId) {
        return masterRepo.getUserRepo().findById(userId).
                orElseThrow(() -> new ResourceNotFound("User Not Exists With Id :" + userId));
    }


    @Override
    @Transactional
    public ResponseModel depositToAccount(Long userId, Long depositAmount) {
        User user = getUserWithId(userId);
        Account userAccount = user.getAccount();
        userAccount.setAccountBalance(userAccount.getAccountBalance() + depositAmount);
        Account updatedUserAccount = masterRepo.getAccountRepo().save(userAccount);

        Transaction transaction = new Transaction(
                TransactionType.CREDIT,
                depositAmount,
                updatedUserAccount.getAccountBalance(),
                updatedUserAccount,
                user
        );
        masterRepo.getTransactionRepo().save(transaction);

        return CommonResponse.OK(AccountDTO.accountToAccountDTO(updatedUserAccount));
    }

    @Override
    @Transactional
    public ResponseModel withdrawFromAccount(Long userId, Long withdrawAmount) {
        User user = getUserWithId(userId);
        Account userAccount = user.getAccount();

        if (userAccount.getAccountBalance() < withdrawAmount) {
            throw new InvalidInputException("withdrawAmount is higher than account balance, accountBalance : "+ userAccount.getAccountBalance());
        }
        userAccount.setAccountBalance(userAccount.getAccountBalance() - withdrawAmount);
        Account updatedUserAccount = masterRepo.getAccountRepo().save(userAccount);

        Transaction transaction = new Transaction(
                TransactionType.DEBIT,
                withdrawAmount,
                updatedUserAccount.getAccountBalance(),
                updatedUserAccount,
                user
        );
        masterRepo.getTransactionRepo().save(transaction);

        return CommonResponse.OK(AccountDTO.accountToAccountDTO(updatedUserAccount));
    }

    @Override
    public ResponseModel getAccountDetails(Long userId) {
        User user = getUserWithId(userId);
        return CommonResponse.OK(AccountDTO.accountToAccountDTO(user.getAccount()));
    }


}
