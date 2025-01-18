package com.munaf.ERP_SYSTEM.controllers;

import com.munaf.ERP_SYSTEM.services.AccountService;
import com.munaf.ERP_SYSTEM.utils.ResponseModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/{userId}/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("deposit/{depositAmount}")
    public ResponseModel depositToAccount(@PathVariable Long userId, @PathVariable Long depositAmount) {
        return accountService.depositToAccount(userId,depositAmount);
    }

    @PostMapping("withdraw/{withdrawAmount}")
    public ResponseModel withdrawFromAccount(@PathVariable Long userId, @PathVariable Long withdrawAmount) {
        return accountService.withdrawFromAccount(userId,withdrawAmount);
    }

    @GetMapping()
    public ResponseModel getAccountDetails(@PathVariable Long userId) {
        return accountService.getAccountDetails(userId);
    }

}
