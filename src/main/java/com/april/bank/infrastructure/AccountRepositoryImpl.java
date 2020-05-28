package com.april.bank.infrastructure;

import com.april.bank.domain.Account;
import com.april.bank.domain.Person;
import com.april.bank.domain.repository.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private static final Map<Long, Account> DATABASE = new ConcurrentHashMap<>();
    private static AtomicLong id = new AtomicLong(1);

    @Override
    public Account create(String firstName, String lastName){
        long accountId = id.getAndIncrement();
        Account account = new Account(accountId, new Person(firstName, lastName));
        DATABASE.put(accountId, account);
        return account;
    }

    @Override
    public Optional<Account> findById(Long id){
        return Optional.ofNullable(DATABASE.get(id));
    }

}
