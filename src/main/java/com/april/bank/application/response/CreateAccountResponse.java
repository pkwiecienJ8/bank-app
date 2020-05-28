package com.april.bank.application.response;

import java.math.BigDecimal;

public class CreateAccountResponse {

    private long id;

    private String firstName;

    private String lastName;

    private BigDecimal balance;

    public static final class Builder {
        private long id;
        private String firstName;
        private String lastName;
        private BigDecimal balance;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder balance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public CreateAccountResponse build() {
            CreateAccountResponse response = new CreateAccountResponse();
            response.id = this.id;
            response.firstName = this.firstName;
            response.lastName = this.lastName;
            response.balance = this.balance;
            return response;
        }
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
