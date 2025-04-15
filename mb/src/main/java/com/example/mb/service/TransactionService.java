package com.example.mb.service;

import com.example.mb.model.Transaction;
import com.example.mb.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getTransactionsByAccount(Long accountId) {
        return transactionRepository.findByFromAccountId(accountId);
    }

    public List<Transaction> getTransactionsByType(String type) {
        return transactionRepository.findByTransactionTypeIgnoreCase(type);
    }
}
