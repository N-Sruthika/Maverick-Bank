package com.example.mb.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.dto.BankTransferRequest;
import com.example.mb.dto.UPITransactionRequest;
import com.example.mb.exception.InsufficientBalanceException;
import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.model.BankTransfer;
import com.example.mb.model.Beneficiary;
import com.example.mb.model.Transaction;
import com.example.mb.model.UPITransaction;
import com.example.mb.repository.AccountRepository;
import com.example.mb.repository.BankTransferRepository;
import com.example.mb.repository.BeneficiaryRepository;
import com.example.mb.repository.TransactionRepository;
import com.example.mb.repository.UPITransactionRepository;

@Service
public class TransactionService {

    @Autowired private TransactionRepository transactionRepository;
    @Autowired private BankTransferRepository bankTransferRepository;
    @Autowired private UPITransactionRepository upiTransactionRepository;
    @Autowired private AccountRepository accountRepository;
    @Autowired private BeneficiaryRepository beneficiaryRepository;

    Logger logger = LoggerFactory.getLogger("TransactionService");

    // Method for making bank transfer
    public Transaction makeBankTransfer(BankTransferRequest request, String accountNumber) throws InvalidAccountException, InsufficientBalanceException {
        Account fromAccount = accountRepository.findByAccountNumber(accountNumber);

        if (fromAccount == null) {
            throw new InvalidAccountException("From Account not found");
        }

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        Beneficiary beneficiary = beneficiaryRepository.findByAccountNumber(request.getAccountNumber());
        if (beneficiary == null) {
            throw new InvalidAccountException("Beneficiary account not found");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount().doubleValue());
        transaction.setTransactionMode("BANK");
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("COMPLETED");
        transaction.setFromAccount(fromAccount);
        transactionRepository.save(transaction);

        BankTransfer bankTransfer = new BankTransfer();
        bankTransfer.setTransaction(transaction);
        bankTransfer.setBeneficiaryAccountNumber(request.getAccountNumber());
        bankTransfer.setBeneficiaryBankName(beneficiary.getBankName());
        bankTransfer.setBeneficiaryName(beneficiary.getName());
        bankTransfer.setBeneficiaryIfsc(beneficiary.getIfsc());
        bankTransfer.setBeneficiaryAccountType(request.getBeneficiaryAccountType());
        bankTransfer.setAmount(request.getAmount());

        bankTransferRepository.save(bankTransfer);

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        accountRepository.save(fromAccount);

        logger.info("Bank transfer completed for account {} to beneficiary {}", accountNumber, request.getAccountNumber());
        return transaction;
    }

    // Method for making UPI transfer
    public Transaction makeUPITransfer(UPITransactionRequest request, String accountNumber) throws InvalidAccountException, InsufficientBalanceException {
        Account fromAccount = accountRepository.findByAccountNumber(accountNumber);

        if (fromAccount == null) {
            throw new InvalidAccountException("From Account not found");
        }

        if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for this transaction.");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount().doubleValue());
        transaction.setTransactionMode("UPI");
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("COMPLETED");
        transaction.setFromAccount(fromAccount);
        transactionRepository.save(transaction);

        UPITransaction upi = new UPITransaction();
        upi.setTransaction(transaction);
        upi.setUpiId(request.getUpiId());
        upi.setAmount(request.getAmount().toPlainString());
        upiTransactionRepository.save(upi);

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
        accountRepository.save(fromAccount);

        logger.info("UPI transfer completed for account {} to UPI ID {}", accountNumber, request.getUpiId());
        return transaction;
    }

    // Fetch transaction history by account number
    public List<Transaction> getTransactionHistory(String accountNumber) {
        List<Transaction> transactions = transactionRepository.findByFromAccount_AccountNumber(accountNumber);
        logger.info("Fetched transaction history for account {}", accountNumber);
        return transactions;
    }
}
