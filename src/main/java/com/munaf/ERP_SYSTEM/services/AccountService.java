package com.munaf.ERP_SYSTEM.services;

import com.munaf.ERP_SYSTEM.utils.ResponseModel;

public interface AccountService {
    ResponseModel depositToAccount(Long userId, Long depositAmount);

    ResponseModel withdrawFromAccount(Long userId, Long withdrawAmount);

    ResponseModel getAccountDetails(Long userId);
}
