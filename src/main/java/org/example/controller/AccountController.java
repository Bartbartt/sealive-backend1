package org.example.controller;
import org.example.model.Account;
import org.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("create")
    public CompletableFuture<ResponseEntity<Account>> create(@RequestBody Account account){
        return accountService.create(account)
                .thenApply(createdAccount -> new ResponseEntity<>(createdAccount, HttpStatus.CREATED));
    }
    @GetMapping("getAll")
    public CompletableFuture<ResponseEntity<List<Account>>> getAll(){
        return accountService.getAll()
                .thenApply(ResponseEntity::ok);
    }
}
