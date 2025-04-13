package com.example.mb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // Create account
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(long accountId) throws InvalidAccountException {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isEmpty()) {
            throw new InvalidAccountException("Account not found for the given ID.");
        }
        return accountOpt.get();
    }
    public List<Account> getAccountsByCustomerId(long customerId) {
        // Return list of accounts for the customer, or an empty list if not found
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        if (accounts.isEmpty()) {
            // Handle the case where no accounts are found for the given customer ID
            return new ArrayList<>();  // Return an empty list if no accounts exist
        }
        return accounts;
    }
    public Account updateAccount(Account account) {
        // Save the updated account
        return accountRepository.save(account);
    }




}
