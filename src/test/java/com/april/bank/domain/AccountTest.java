package com.april.bank.domain;

import com.april.bank.domain.exception.NotEnoughMoneyException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest {

    private Account account;

    @Before
    public void init(){
        account = new Account(1, new Person("Przemyslaw", "Kwiecien"));
    }

    @Test
    public void afterAccountIsCreatedShouldHaveZeroBalance(){
        assertEquals(account.getBalance(), BigDecimal.ZERO);
        assertTrue(account.getBalanceHistory().isEmpty());
    }

    @Test
    public void shouldIncreaseBalanceAfterDeposit(){
        account.deposit(BigDecimal.TEN);
        assertEquals(account.getBalance(), BigDecimal.TEN);
        assertEquals(account.getBalanceHistory().size(), 1);
    }

    @Test
    public void shouldDecreaseBalanceAfterWithdraw() {
        account.deposit(BigDecimal.TEN);
        account.withdraw(BigDecimal.ONE);
        assertEquals(account.getBalance(), new BigDecimal(9));
        assertEquals(account.getBalanceHistory().size(), 2);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void shouldThrowNotEnoughMoneyExceptionIFWithdrawMoneyAmountIsBiggerThanBalance() {
        account.withdraw(BigDecimal.TEN);
    }

    @Test
    public void afterWithdrawLastOperationShouldContainNegativeValueInHistory() {
        account.deposit(BigDecimal.TEN);
        account.withdraw(BigDecimal.ONE);
        List<BigDecimal> balanceHistory = account.getBalanceHistory();
        assertTrue(balanceHistory.get(balanceHistory.size() - 1).compareTo(BigDecimal.ZERO) < 0);
    }
}
