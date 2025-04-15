package com.example.mb.controller;

import com.example.mb.model.Account;
import com.example.mb.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Account>> getAccountByName(@PathVariable String name) {
        return ResponseEntity.ok(accountService.getAccountsByCustomerName(name));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<Account>> getAccountByBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(accountService.getAccountsByBranch(branchId));
    }
}
