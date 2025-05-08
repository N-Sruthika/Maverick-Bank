package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock private TransactionRepository transactionRepository;
    @Mock private BankTransferRepository bankTransferRepository;
    @Mock private UPITransactionRepository upiTransactionRepository;
    @Mock private AccountRepository accountRepository;
    @Mock private BeneficiaryRepository beneficiaryRepository;

    @InjectMocks
    private TransactionService transactionService;

    private Account fromAccount;
    private Beneficiary beneficiary;
    private BankTransfer bankTransfer;
    private UPITransaction upiTransaction;
    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        // Mock account setup
        fromAccount = new Account();
        fromAccount.setId(1L);
        fromAccount.setAccountNumber("1234567890");
        fromAccount.setBalance(new BigDecimal("1000.00"));

        // Mock beneficiary setup
        beneficiary = new Beneficiary();
        beneficiary.setAccountNumber("0987654321");
        beneficiary.setBankName("BankName");
        beneficiary.setName("BeneficiaryName");
        beneficiary.setIfsc("IFSC1234");

        // Mock bank transfer setup
        bankTransfer = new BankTransfer();
        bankTransfer.setAmount(new BigDecimal("200.00"));
        bankTransfer.setBeneficiaryAccountNumber("0987654321");

        // Mock UPI transaction setup
        upiTransaction = new UPITransaction();
        upiTransaction.setAmount(200.00);
        upiTransaction.setUpiId("upi123");
        
        // Mock transaction setup
        transaction = new Transaction();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionMode("BANK");
        transaction.setTransactionType("TRANSFER");
        transaction.setStatus("COMPLETED");
        transaction.setAmount(200.00);
        transaction.setFromAccount(fromAccount);
    }

    @Test
    public void testMakeBankTransfer_success() throws Exception {
        // Mock repository responses
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(fromAccount);
        when(beneficiaryRepository.findByAccountNumber("0987654321")).thenReturn(beneficiary);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(bankTransferRepository.save(any(BankTransfer.class))).thenReturn(bankTransfer);
        when(accountRepository.save(any(Account.class))).thenReturn(fromAccount);

        // Call the service to make a bank transfer
        Transaction result = transactionService.makeBankTransfer(bankTransfer, "1234567890");

        // Assert the results
        assertEquals("COMPLETED", result.getStatus()); // Ensure transaction status is 'COMPLETED'
        verify(transactionRepository).save(any(Transaction.class)); // Verify transaction save
        verify(bankTransferRepository).save(any(BankTransfer.class)); // Verify bank transfer save
        verify(accountRepository).save(any(Account.class)); // Verify account update
    }

    @Test
    public void testMakeUPITransaction_success() throws Exception {
        // Mock repository responses for UPI transaction
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(fromAccount);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        when(upiTransactionRepository.save(any(UPITransaction.class))).thenReturn(upiTransaction);
        when(accountRepository.save(any(Account.class))).thenReturn(fromAccount);

        // Call the service to make a UPI transaction
        Transaction result = transactionService.makeUPITransfer(upiTransaction, "1234567890");

        // Assert the results
        assertEquals("COMPLETED", result.getStatus()); // Ensure transaction status is 'COMPLETED'
        verify(transactionRepository).save(any(Transaction.class)); // Verify transaction save
        verify(upiTransactionRepository).save(any(UPITransaction.class)); // Verify UPI transaction save
        verify(accountRepository).save(any(Account.class)); // Verify account update
    }

   
}
