package com.example.mb.service;

import com.example.mb.model.TransactionLimit;
import com.example.mb.exception.InvalidIdException;
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
//add transaction limit
    public TransactionLimit addTransactionLimit(Long accountId, TransactionLimit request) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isEmpty())
            throw new RuntimeException("Account not found with ID: " + accountId);
        Account account = optionalAccount.get();
        request.setAccount(account);
        logger.info("Transaction limit added for account ID {}", accountId);
        return limitRepository.save(request);
    }

    public TransactionLimit updateTransactionLimit(Long id, TransactionLimit newLimit) throws InvalidIdException {
        Optional<TransactionLimit> optionalOldLimit = limitRepository.findById(id);
        if (optionalOldLimit.isEmpty()) {
            throw new InvalidIdException("TransactionLimit not found with ID: " + id);
        }

        TransactionLimit oldLimit = optionalOldLimit.get();

        if (newLimit.getDailyLimit() != null) {
            oldLimit.setDailyLimit(newLimit.getDailyLimit());
        }
        if (newLimit.getMonthlyLimit() != null) {
            oldLimit.setMonthlyLimit(newLimit.getMonthlyLimit());
        }

        logger.info("Transaction limit updated for ID {}", id);
        return limitRepository.save(oldLimit);
    }


    public TransactionLimit getLimitByAccountNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        logger.info("Fetched transaction limit for account number {}", accountNumber);
        return limitRepository.findByAccount(account);
    }


}
