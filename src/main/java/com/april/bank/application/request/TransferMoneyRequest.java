package com.april.bank.application.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class TransferMoneyRequest {
    @NotNull
    private long accountId;
    @Positive
    @NotNull
    private BigDecimal amount;

    public TransferMoneyRequest(long accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}
