package com.april.bank.application.response;

import java.math.BigDecimal;

public class FindBalanceResponse {

    private BigDecimal balance;

    public FindBalanceResponse(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
