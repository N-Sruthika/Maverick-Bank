package com.example.mb.service;

import com.example.mb.model.TransactionLimit;
import com.example.mb.model.Account;
import com.example.mb.repository.TransactionLimitRepository;
import com.example.mb.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionLimitService {

    @Autowired
    private TransactionLimitRepository limitRepository;

    @Autowired
    private AccountRepository accountRepository;

    Logger logger = LoggerFactory.getLogger("TransactionLimitService");

    public TransactionLimit addTransactionLimit(Long accountId, TransactionLimit request) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty()) {
            throw new RuntimeException("Account not found with ID: " + accountId);
        }

        TransactionLimit limit = new TransactionLimit();
        limit.setAccount(optionalAccount.get());
        limit.setDailyLimit(request.getDailyLimit());
        limit.setMonthlyLimit(request.getMonthlyLimit());

        TransactionLimit savedLimit = limitRepository.save(limit);
        logger.info("Transaction limit added for account ID {}", accountId);
        return savedLimit;
    }

    public TransactionLimit updateTransactionLimit(Long id, TransactionLimit request) {
        Optional<TransactionLimit> optionalLimit = limitRepository.findById(id);
        if (optionalLimit.isEmpty()) {
            throw new RuntimeException("TransactionLimit not found with ID: " + id);
        }

        TransactionLimit existing = optionalLimit.get();
        existing.setDailyLimit(request.getDailyLimit());
        existing.setMonthlyLimit(request.getMonthlyLimit());

        TransactionLimit updatedLimit = limitRepository.save(existing);
        logger.info("Transaction limit updated for ID {}", id);
        return updatedLimit;
    }

    public TransactionLimit getLimitByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new RuntimeException("Account not found with number: " + accountNumber);
        }

        TransactionLimit limit = limitRepository.findByAccountId(account.getId());
        if (limit == null) {
            throw new RuntimeException("TransactionLimit not set for this account.");
        }

        logger.info("Fetched transaction limit for account number {}", accountNumber);
        return limit;
    }
}
