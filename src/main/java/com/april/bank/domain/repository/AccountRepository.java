package com.april.bank.domain.repository;

import com.april.bank.domain.Account;

import java.util.Collection;
import java.util.Optional;

public interface AccountRepository {
    Account create(String firstName, String lastName);

    Optional<Account> findById(Long id);
}
