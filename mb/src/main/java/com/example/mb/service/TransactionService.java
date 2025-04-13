package com.example.mb.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.model.BankTransfer;
import com.example.mb.model.Transaction;
import com.example.mb.model.TransactionLimit;
import com.example.mb.model.UPITransaction;
import com.example.mb.repository.BankTransferRepository;
import com.example.mb.repository.TransactionLimitRepository;
import com.example.mb.repository.TransactionRepository;
import com.example.mb.repository.UPITransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UPITransactionRepository upiTransactionRepository;

    @Autowired
    private BankTransferRepository bankTransferRepository;

    @Autowired
    private TransactionLimitRepository transactionLimitRepository;

    public Transaction makeUPITransaction(UPITransaction upiTransaction) {
        Long accountId = upiTransaction.getTransaction().getFromAccount().getId();
        BigDecimal amount = new BigDecimal(upiTransaction.getAmount()); // Ensure correct type conversion

        // Retrieve transaction limits for the account
        TransactionLimit limit = transactionLimitRepository.findByAccountId(accountId);

        if (limit == null) {
            throw new RuntimeException("Transaction limit not found for this account.");
        }

        // Check if the transaction exceeds daily or monthly limits
        if (amount.compareTo(limit.getDailyLimit()) > 0) {
            throw new RuntimeException("Daily transaction limit exceeded.");
        }

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount.doubleValue());
        transaction.setTransactionType("UPI");
        transaction.setStatus("PENDING");
        transaction.setTransactionMode("UPI");
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        // Save the UPI transaction details
        upiTransaction.setTransaction(transaction);
        upiTransactionRepository.save(upiTransaction);

        // Update status to "COMPLETED"
        transaction.setStatus("COMPLETED");
        return transactionRepository.save(transaction);
    }

    public Transaction makeBankTransfer(BankTransfer bankTransfer) {
        Long accountId = bankTransfer.getTransaction().getFromAccount().getId();
        
        BigDecimal amount = bankTransfer.getAmount();  // Direct assignment

        // Retrieve transaction limits for the account
        TransactionLimit limit = transactionLimitRepository.findByAccountId(accountId);

        if (limit == null) {
            throw new RuntimeException("Transaction limit not found for this account.");
        }

        // Check if the transaction exceeds daily or monthly limits
        if (amount.compareTo(limit.getDailyLimit()) > 0) {
            throw new RuntimeException("Daily transaction limit exceeded.");
        }

        // Create and save the transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount.doubleValue());
        transaction.setTransactionType("BANK");
        transaction.setStatus("PENDING");
        transaction.setTransactionMode("BANK");
        transaction.setTransactionDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        // Save the bank transfer details
        bankTransfer.setTransaction(transaction);
        bankTransferRepository.save(bankTransfer);

        // Update status to "COMPLETED"
        transaction.setStatus("COMPLETED");
        return transactionRepository.save(transaction);
    }
    public List<Transaction> getTransactionHistory(Long accountId) {
        // Fetch the transaction history by accountId from the database
        return transactionRepository.findByFromAccountId(accountId);
    }
}
