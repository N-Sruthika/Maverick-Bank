package com.example.mb.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.repository.AccountRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger("AccountService");

    // Create account
    public Account createAccount(Account account) {
        logger.info("Creating a new account for customer ID: {}", account.getCustomer().getId());
        return accountRepository.save(account);
    }

    public Account getAccountById(long accountId) throws InvalidAccountException {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isEmpty()) {
            logger.warn("Account not found for ID: {}", accountId);
            throw new InvalidAccountException("Account not found for the given ID.");
        }
        logger.info("Account retrieved for ID: {}", accountId);
        return accountOpt.get();
    }

    public List<Account> getAccountsByCustomerId(long customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        logger.info("Retrieved {} accounts for customer ID: {}", accounts.size(), customerId);
        return accounts.isEmpty() ? new ArrayList<>() : accounts;
    }

    public Account updateAccount(Account account) {
        logger.info("Updating account with ID: {}", account.getId());
        return accountRepository.save(account);
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            logger.error("No account found with account number: {}", accountNumber);
            throw new RuntimeException("Account not found for account number: " + accountNumber);
        }
        logger.info("Account found for account number: {}", accountNumber);
        return account;
    }

    public BigDecimal getBalance(String accountNumber) throws InvalidAccountException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            logger.warn("Balance check failed. No account found with number: {}", accountNumber);
            throw new InvalidAccountException("No account found with account number: " + accountNumber);
        }
        logger.info("Balance retrieved for account number: {}", accountNumber);
        return account.getBalance();
    }
}
