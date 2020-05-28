package com.april.bank.domain.service;

import com.april.bank.application.request.CreateAccountRequest;
import com.april.bank.application.request.TransferMoneyRequest;
import com.april.bank.application.response.CreateAccountResponse;
import com.april.bank.application.response.FindBalanceResponse;
import com.april.bank.domain.Account;
import com.april.bank.domain.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest client){
        Account account = accountRepository.create(client.getFirstName(), client.getLastName());
        return new CreateAccountResponse.Builder()
                .id(account.getId())
                .firstName(account.getOwner().getFirstName())
                .lastName(account.getOwner().getLastName())
                .balance(account.getBalance())
                .build();
    }

    @Override
    public FindBalanceResponse find(long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Account with given id does not exist"));
        return new FindBalanceResponse(account.getBalance());
    }

    @Override
    public void deposit(TransferMoneyRequest transferMoneyRequest) {
        Account account = accountRepository.findById(transferMoneyRequest.getAccountId()).orElseThrow(() -> new ObjectNotFoundException("Account with given id does not exist"));
        account.deposit(transferMoneyRequest.getAmount());
    }

    @Override
    public void withdraw(TransferMoneyRequest transferMoneyRequest) {
        Account account = accountRepository.findById(transferMoneyRequest.getAccountId()).orElseThrow(() -> new ObjectNotFoundException("Account with given id does not exist"));
        account.withdraw(transferMoneyRequest.getAmount());
    }
}
