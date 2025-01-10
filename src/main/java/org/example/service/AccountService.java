package org.example.service;
import org.example.model.Account;
import org.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Async
    public CompletableFuture<Account> create(Account account){
        return CompletableFuture.supplyAsync(() -> accountRepository.save(account));
    }

    @Async
    public CompletableFuture<List<Account>> getAll() {
        return CompletableFuture.supplyAsync(() -> accountRepository.findAll());
    }
}