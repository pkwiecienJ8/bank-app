package com.april.bank.domain.service;

import com.april.bank.application.request.CreateAccountRequest;
import com.april.bank.application.request.TransferMoneyRequest;
import com.april.bank.application.response.CreateAccountResponse;
import com.april.bank.application.response.FindBalanceResponse;

public interface AccountService {
    CreateAccountResponse createAccount(CreateAccountRequest client);

    FindBalanceResponse find(long id);

    void deposit(TransferMoneyRequest transferMoneyRequest);

    void withdraw(TransferMoneyRequest transferMoneyRequest);
}
