package com.april.bank.domain.service;

import com.april.bank.application.request.CreateAccountRequest;
import com.april.bank.application.request.TransferMoneyRequest;
import com.april.bank.application.response.CreateAccountResponse;
import com.april.bank.domain.Account;
import com.april.bank.domain.Person;
import com.april.bank.infrastructure.AccountRepositoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    private AccountRepositoryImpl repository;

    private AccountService service;

    @Before
    public void init(){
        service = new AccountServiceImpl(repository);
    }

    @Test
    public void shouldReturnResponseAfterCreateRequest() {
        CreateAccountRequest request = new CreateAccountRequest("Przemek", "Kwiecien");
        Mockito.when(repository.create(request.getFirstName(), request.getLastName())).thenReturn(new Account(1, new Person(request.getFirstName(), request.getLastName())));

        CreateAccountResponse response = service.createAccount(request);
        assertEquals(response.getFirstName(), "Przemek");
        assertEquals(response.getLastName(), "Kwiecien");
        assertEquals(response.getId(), 1);
        assertEquals(response.getBalance(), BigDecimal.ZERO);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowExceptionIfAccountNotFoundInDeposit(){
        TransferMoneyRequest request = prepareTransactionMethod();
        service.deposit(request);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void shouldThrowExceptionIfAccountNotFoundInWithdraw(){
        TransferMoneyRequest request = prepareTransactionMethod();
        service.withdraw(request);
    }

    private TransferMoneyRequest prepareTransactionMethod(){
        TransferMoneyRequest request = new TransferMoneyRequest(99, BigDecimal.TEN);
        Mockito.when(repository.findById(request.getAccountId()))
                .thenReturn(Optional.empty());
        return request;
    }
}
