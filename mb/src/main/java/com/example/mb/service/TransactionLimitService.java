package com.example.mb.service;

import com.example.mb.model.TransactionLimit;
import com.example.mb.model.Account;
import com.example.mb.repository.TransactionLimitRepository;
import com.example.mb.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionLimitService {

    @Autowired
    private TransactionLimitRepository limitRepository;

    @Autowired
    private AccountRepository accountRepository;

    public TransactionLimit addTransactionLimit(Long accountId, TransactionLimit request) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }

        TransactionLimit limit = new TransactionLimit();
        limit.setAccount(optionalAccount.get());
        limit.setDailyLimit(request.getDailyLimit());
        limit.setMonthlyLimit(request.getMonthlyLimit());

        return limitRepository.save(limit);
    }

    public TransactionLimit updateTransactionLimit(Long id, TransactionLimit request) {
        Optional<TransactionLimit> optionalLimit = limitRepository.findById(id);
        if (optionalLimit.isEmpty()) {
            throw new RuntimeException("TransactionLimit not found with ID: " + id);
        }

        TransactionLimit existing = optionalLimit.get();
        existing.setDailyLimit(request.getDailyLimit());
        existing.setMonthlyLimit(request.getMonthlyLimit());

        return limitRepository.save(existing);
    }

    public TransactionLimit getLimitByAccountNumber(String accountNumber) {
        // Retrieve the account based on the account number
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found with number: " + accountNumber);
        }

        // Retrieve the transaction limit for the found account
        TransactionLimit limit = limitRepository.findByAccountId(account.getId());

        if (limit == null) {
            throw new RuntimeException("TransactionLimit not set for this account.");
        }

        return limit;
    }
}
