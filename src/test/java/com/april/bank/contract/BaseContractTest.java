package com.april.bank.contract;

import com.april.bank.BankApplication;
import com.april.bank.application.rest.AccountController;
import com.april.bank.domain.Account;
import com.april.bank.domain.repository.AccountRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BankApplication.class)
public abstract class BaseContractTest {

    @Autowired
    AccountController accountController;

    @Autowired
    private AccountRepository repository;

    private static boolean initialized = false;

    @Before
    public void setUp(){

        if(!initialized){
            Account account = repository.create("Przemyslaw", "Kwiecien");
            account.deposit(new BigDecimal(100));
            initialized = true;
        }

        RestAssuredMockMvc.standaloneSetup(accountController);
    }

}
