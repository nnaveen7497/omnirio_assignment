package com.omnirio.accountservice.resource;

import com.omnirio.accountservice.model.Account;
import com.omnirio.accountservice.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/accounts")
public class AccountResource {

    private final AccountService accountService;

    AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllEventDataMaster() {
        List<Account> accountDtoList = accountService.getAccountList();
        return ResponseEntity.ok(accountDtoList);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account savedAccount = accountService.saveAccountDetails(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }
}
