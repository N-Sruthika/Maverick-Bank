package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
import com.example.mb.model.Beneficiary; // Import Beneficiary
import com.example.mb.model.Transaction;
import com.example.mb.model.UPITransaction;
import com.example.mb.repository.AccountRepository;
import com.example.mb.repository.BankTransferRepository;
import com.example.mb.repository.BeneficiaryRepository;  // Mock the BeneficiaryRepository
import com.example.mb.repository.TransactionRepository;
import com.example.mb.repository.UPITransactionRepository;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private BankTransferRepository bankTransferRepository;

    @Mock
    private UPITransactionRepository upiTransactionRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BeneficiaryRepository beneficiaryRepository;  // Mock the BeneficiaryRepository

    private Account fromAccount;
    private BankTransfer bankTransfer;
    private Transaction transaction;
    private Beneficiary beneficiary; // Mocked Beneficiary object

    @BeforeEach
    public void init() {
        // Initialize sample data for testing
        fromAccount = new Account();
        fromAccount.setAccountNumber("FROM123");
        fromAccount.setBalance(new BigDecimal("10000.00"));
        
        bankTransfer = new BankTransfer();
        bankTransfer.setAmount(new BigDecimal("5000.00"));
        bankTransfer.setBeneficiaryAccountNumber("TO123");

        beneficiary = new Beneficiary(); // Create a mocked Beneficiary object
        beneficiary.setAccountNumber("TO123");
        beneficiary.setName("John Doe");

        transaction = new Transaction();
        transaction.setAmount(5000.00);
        transaction.setTransactionMode("BANK");
        transaction.setTransactionType("TRANSFER");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setStatus("COMPLETED");
    }

    @Test
    public void testMakeBankTransferSuccess() throws InvalidAccountException, InsufficientBalanceException {
        // Arrange: Mock the account and beneficiary repository
        when(accountRepository.findByAccountNumber("FROM123")).thenReturn(fromAccount);
        when(beneficiaryRepository.findByAccountNumber("TO123")).thenReturn(beneficiary);  // Mock beneficiary retrieval
        when(bankTransferRepository.save(bankTransfer)).thenReturn(bankTransfer);
        
        // Act: Execute the bank transfer service method
        Transaction result = transactionService.makeBankTransfer(bankTransfer, "FROM123");
        
        // Assert: Verify expected results
        assertEquals("BANK", result.getTransactionMode());
        assertEquals("TRANSFER", result.getTransactionType());
        assertEquals(5000.00, result.getAmount());
        assertEquals("COMPLETED", result.getStatus());
        assertEquals(new BigDecimal("5000.00"), fromAccount.getBalance());  // Ensure balance is updated
    }

  
    @Test
    public void testMakeUPITransferSuccess() throws InvalidAccountException, InsufficientBalanceException {
        // Arrange: Mock the account and UPI transaction repository
        when(accountRepository.findByAccountNumber("FROM123")).thenReturn(fromAccount);
        when(upiTransactionRepository.save(any(UPITransaction.class))).thenReturn(new UPITransaction());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        // Act: Execute UPI transfer
        Transaction result = transactionService.makeUPITransfer(transaction, "FROM123");

        // Assert: Verify expected results for UPI transfer
        assertEquals("UPI", result.getTransactionMode());
        assertEquals("TRANSFER", result.getTransactionType());
        assertEquals(5000.00, result.getAmount());
        assertEquals("COMPLETED", result.getStatus());

        // Verify that repositories were called
        verify(transactionRepository, times(1)).save(transaction);
        verify(upiTransactionRepository, times(1)).save(any(UPITransaction.class));
    }

   
    @Test
    public void testGetTransactionHistory() {
        // Arrange: Mock transaction history retrieval
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(1000.00);
        transaction1.setTransactionMode("BANK");
        transaction1.setTransactionDate(LocalDateTime.now());
        
        List<Transaction> transactions = Arrays.asList(transaction1);
        when(transactionRepository.findByFromAccount_AccountNumber("FROM123")).thenReturn(transactions);

        // Act: Retrieve transaction history
        List<Transaction> result = transactionService.getTransactionHistory("FROM123");

        // Assert: Verify transaction history
        assertEquals(1, result.size());
        assertEquals(1000.00, result.get(0).getAmount());
    }
}
