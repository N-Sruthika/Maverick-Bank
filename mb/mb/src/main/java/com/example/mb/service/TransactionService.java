package com.example.mb.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        
        if (fromAccount == null) {
            throw new InvalidAccountException("From Account not found");
        }
        
        // Check if balance is sufficient
        if (fromAccount.getBalance().compareTo(bankTransfer.getAmount()) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        
        // Fetch beneficiary details
        Beneficiary beneficiary = beneficiaryRepository.findByAccountNumber(bankTransfer.getBeneficiaryAccountNumber());
        if (beneficiary == null) {
            throw new InvalidAccountException("Beneficiary account not found");
        }
        
        // Create a new transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(bankTransfer.getAmount().doubleValue());
        transaction.setTransactionMode("BANK");
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("COMPLETED");
        transaction.setFromAccount(fromAccount);
        transactionRepository.save(transaction);
        
        // Set up BankTransfer details
        bankTransfer.setTransaction(transaction);
        bankTransfer.setBeneficiaryAccountNumber(bankTransfer.getBeneficiaryAccountNumber());
        bankTransfer.setBeneficiaryBankName(beneficiary.getBankName());
        bankTransfer.setBeneficiaryName(beneficiary.getName());
        bankTransfer.setBeneficiaryIfsc(beneficiary.getIfsc());
        bankTransferRepository.save(bankTransfer);
        
        // Deduct balance from the sender's account
        fromAccount.setBalance(fromAccount.getBalance().subtract(bankTransfer.getAmount()));
        accountRepository.save(fromAccount);

        logger.info("Bank transfer completed for account {} to beneficiary {}", accountNumber, bankTransfer.getBeneficiaryAccountNumber());
        return transaction;
    }

    // Method for making UPI transfer
    public Transaction makeUPITransfer(Transaction transaction, String accountNumber) throws InvalidAccountException, InsufficientBalanceException {
        // Fetch from account by account number
        Account fromAccount = accountRepository.findByAccountNumber(accountNumber);
        
        if (fromAccount == null) {
            throw new InvalidAccountException("From Account not found");
        }

        // Check if balance is sufficient
        if (fromAccount.getBalance().compareTo(BigDecimal.valueOf(transaction.getAmount())) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for this transaction.");
        }

        // Set transaction fields and save it first
        transaction.setTransactionMode("UPI");
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("COMPLETED");
        transaction.setFromAccount(fromAccount);
        transactionRepository.save(transaction); // ✅ Save before linking to UPI

        // Now create and save UPITransaction
        UPITransaction upiTransaction = new UPITransaction();
        upiTransaction.setTransaction(transaction); // ✅ Now transaction has ID
        upiTransaction.setAmount(transaction.getAmount().toString());
        upiTransaction.setUpiId("dummy@upi");
        
        // or get it from a request field if needed
        upiTransactionRepository.save(upiTransaction);

        // Deduct balance from the sender's account
        fromAccount.setBalance(fromAccount.getBalance().subtract(BigDecimal.valueOf(transaction.getAmount())));
        accountRepository.save(fromAccount);

        logger.info("UPI transfer completed for account {} to UPI ID {}", accountNumber, upiTransaction.getUpiId());
        return transaction;
    }
    public List<Transaction> getTransactionHistory(String accountNumber) {
        // Fetching transactions by account number from the repository
        List<Transaction> transactions = transactionRepository.findByFromAccount_AccountNumber(accountNumber);
        
        // You can skip manually setting the account number now, as it's already present in the Transaction entity
        return transactions;
    }

	public List<Transaction> getTransactionHistoryByAccountId(int aid) {
		
		return transactionRepository.findById(aid);
	}

	public List<Transaction> getTransactionHistoryByCustomerId(int cid) {
		// TODO Auto-generated method stub
		return transactionRepository.findById(cid);
	}

	public List<Transaction> getTransactionsByCustomerId(Long customerId) {
        return transactionRepository.findByFromAccountCustomerId(customerId);
    }
	

}
