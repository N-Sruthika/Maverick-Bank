package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.mb.dto.UPITransactionRequest;
import com.example.mb.exception.InsufficientBalanceException;
import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.model.Transaction;
import com.example.mb.repository.AccountRepository;
import com.example.mb.repository.TransactionRepository;
import com.example.mb.repository.UPITransactionRepository;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UPITransactionRepository upiTransactionRepository;

    private Account account;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks

        // Sample account with balance
        account = new Account();
        account.setAccountNumber("1234567890");
        account.setBalance(new BigDecimal("5000.00"));
    }

    @Test
    public void testMakeUPITransfer_Valid() throws InvalidAccountException, InsufficientBalanceException {
        UPITransactionRequest request = new UPITransactionRequest();
        request.setAmount(new BigDecimal("1000.00"));
        request.setUpiId("upixyz123");

        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(account);

        Transaction transaction = transactionService.makeUPITransfer(request, "1234567890");

        BigDecimal expectedAmount = new BigDecimal("1000.00").setScale(2);
        BigDecimal actualAmount = new BigDecimal(transaction.getAmount()).setScale(2);

        assertEquals(expectedAmount, actualAmount);

        verify(accountRepository, times(1)).findByAccountNumber("1234567890");
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    public void testMakeUPITransfer_InsufficientBalance() {
        UPITransactionRequest request = new UPITransactionRequest();
        request.setAmount(new BigDecimal("6000.00")); // More than balance
        request.setUpiId("upixyz123");

        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(account);

        assertThrows(InsufficientBalanceException.class, () -> {
            transactionService.makeUPITransfer(request, "1234567890");
        });

        verify(accountRepository, times(1)).findByAccountNumber("1234567890");
    }

    @Test
    public void testMakeUPITransfer_InvalidAccount() {
        UPITransactionRequest request = new UPITransactionRequest();
        request.setAmount(new BigDecimal("1000.00"));
        request.setUpiId("upixyz123");

        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(null); // Simulate invalid account

        assertThrows(InvalidAccountException.class, () -> {
            transactionService.makeUPITransfer(request, "1234567890");
        });

        verify(accountRepository, times(1)).findByAccountNumber("1234567890");
    }

    @Test
    public void testMakeUPITransfer_SavesTransactionCorrectly() throws InvalidAccountException, InsufficientBalanceException {
        UPITransactionRequest request = new UPITransactionRequest();
        request.setAmount(new BigDecimal("1000.00"));
        request.setUpiId("upixyz123");

        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(account);

        Transaction transaction = transactionService.makeUPITransfer(request, "1234567890");

        assertEquals("1000.00", new BigDecimal(transaction.getAmount()).setScale(2).toString());

        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }
}
