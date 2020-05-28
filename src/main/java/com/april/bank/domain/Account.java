package com.april.bank.domain;

import com.april.bank.domain.exception.NotEnoughMoneyException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {

    private long id;

    private Person owner;

    private BigDecimal balance;

    private List<BigDecimal> balanceHistory;

    public Account(long id, Person owner) {
        this.id = id;
        this.owner = owner;
        this.balance = BigDecimal.ZERO;
        this.balanceHistory = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public Person getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<BigDecimal> getBalanceHistory() {
        return balanceHistory;
    }

    public void deposit(BigDecimal depositValue){
        synchronized (this){
            this.balance = balance.add(depositValue);
            balanceHistory.add(depositValue);
        }
    }

    public void withdraw(BigDecimal withdrawValue){
        if(balance.subtract(withdrawValue).compareTo(BigDecimal.ZERO) < 0){
            throw new NotEnoughMoneyException("Not enough money to withdraw");
        }

        synchronized (this){
            this.balance = balance.subtract(withdrawValue);
            balanceHistory.add(withdrawValue.negate());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
