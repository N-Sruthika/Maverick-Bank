package com.example.mb.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public Transaction makeBankTransfer(BankTransfer bankTransfer, String accountNumber) throws InvalidAccountException, InsufficientBalanceException {
        // Fetch from account by account number
        Account fromAccount = accountRepository.findByAccountNumber(accountNumber);
       
        // Check if balance is sufficient comparing the account balance with bank transfer amount
        if (fromAccount.getBalance().compareTo(bankTransfer.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        
        // get the account number of beneficiary from bank transfer and Fetch beneficiary details by findByAccountNumber method
        Beneficiary beneficiary = beneficiaryRepository.findByAccountNumber(bankTransfer.getBeneficiaryAccountNumber());
        if (beneficiary == null) {
            throw new InvalidAccountException("Beneficiary account not found");
        }
        
        // Create a new transaction, set the amount,type,mode,date status, fromaccount and save the transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(bankTransfer.getAmount().doubleValue());
        transaction.setTransactionMode("BANK");
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("COMPLETED");
        transaction.setFromAccount(fromAccount);
        transactionRepository.save(transaction);
        
        // Set up BankTransfer details and save 
        bankTransfer.setTransaction(transaction);
        bankTransfer.setBeneficiaryAccountNumber(bankTransfer.getBeneficiaryAccountNumber());
        bankTransfer.setBeneficiaryBankName(beneficiary.getBankName());
        bankTransfer.setBeneficiaryName(beneficiary.getName());
        bankTransfer.setBeneficiaryIfsc(beneficiary.getIfsc());
        bankTransferRepository.save(bankTransfer);
        
        // Deduct balance from the sender's account and subtract it with actual amount 
        fromAccount.setBalance(fromAccount.getBalance().subtract(bankTransfer.getAmount()));
        accountRepository.save(fromAccount);

        logger.info("Bank transfer completed for account {} to beneficiary {}", accountNumber, bankTransfer.getBeneficiaryAccountNumber());
        return transaction;
    }

    // Method for making UPI transfer
    public Transaction makeUPITransfer(UPITransaction upiTransaction, String accountNumber) 
            throws InvalidAccountException, InsufficientBalanceException {
        // 1.Fetch the  account by account number
        Account fromAccount = accountRepository.findByAccountNumber(accountNumber);
        if (fromAccount == null) {
            throw new InvalidAccountException("From Account not found");
        }

        // Check if balance is sufficient
        BigDecimal upiAmount = new BigDecimal(upiTransaction.getAmount());
        if (fromAccount.getBalance().compareTo(upiAmount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for this transaction.");
        }

        // Create and save a new transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionMode("UPI");
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("COMPLETED");
        transaction.setFromAccount(fromAccount);
        transaction.setAmount(upiTransaction.getAmount());
        transactionRepository.save(transaction);

        // Link the transaction to UPITransaction and save it
        upiTransaction.setTransaction(transaction);
        upiTransactionRepository.save(upiTransaction);

        // Deduct balance from the sender's account
        fromAccount.setBalance(fromAccount.getBalance().subtract(upiAmount));
        accountRepository.save(fromAccount);

        logger.info("UPI transfer completed for account {} to UPI ID {}", accountNumber, upiTransaction.getUpiId());
        return transaction;
    }
 
	public List<Transaction> getTransactionsByCustomerId(Long customerId) {
        return transactionRepository.findByFromAccountCustomerId(customerId);
    }

	public Page<Transaction> getPaginatedTransactionHistoryByCustomerId(Long customerId, Pageable pageable) {
        return transactionRepository.findByFromAccountCustomerId(customerId, pageable);
    }
	

}
