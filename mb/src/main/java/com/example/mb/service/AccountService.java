package com.example.mb.service;

import com.example.mb.model.Account;
import com.example.mb.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAccountsByCustomerName(String name) {
        return accountRepository.findByCustomerNameIgnoreCase(name);
    }

    public List<Account> getAccountsByBranch(Long branchId) {
        return accountRepository.findByBranchId(branchId);
    }
}
