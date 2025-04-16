package com.example.mb.service;

import com.example.mb.model.Account;
import com.example.mb.model.TransactionLimit;
import com.example.mb.repository.AccountRepository;
import com.example.mb.repository.TransactionLimitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

public class TransactionLimitServiceTest {

    @InjectMocks
    private TransactionLimitService transactionLimitService;

    @Mock
    private TransactionLimitRepository limitRepository;

    @Mock
    private AccountRepository accountRepository;

    private Account account;
    private TransactionLimit transactionLimit;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Set up mock account
        account = new Account();
        account.setId(1L);
        account.setAccountNumber("1234567890");

        // Set up mock transaction limit
        transactionLimit = new TransactionLimit();
        transactionLimit.setDailyLimit(new BigDecimal("10000.00"));
        transactionLimit.setMonthlyLimit(new BigDecimal("100000.00"));
    }

    @Test
    public void testAddTransactionLimit_Valid() {
        // Mock repository responses
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        // Perform add transaction limit
        TransactionLimit result = transactionLimitService.addTransactionLimit(1L, transactionLimit);

        // Assert that the transaction limit was set correctly
        
        assertEquals(new BigDecimal("10000.00"), result.getDailyLimit());
        }

    @Test
    public void testAddTransactionLimit_AccountNotFound() {
        // Mock repository responses
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert that exception is thrown when account is not found
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transactionLimitService.addTransactionLimit(1L, transactionLimit);
        });

        assertEquals("Account not found with ID: 1", exception.getMessage());
    }

    @Test
    public void testUpdateTransactionLimit_Valid() {
        // Mock repository responses
        when(limitRepository.findById(1L)).thenReturn(Optional.of(transactionLimit));
        when(limitRepository.save(any(TransactionLimit.class))).thenReturn(transactionLimit);

        // Update the transaction limit
        transactionLimit.setDailyLimit(new BigDecimal("12000.00"));
        transactionLimit.setMonthlyLimit(new BigDecimal("120000.00"));
        TransactionLimit result = transactionLimitService.updateTransactionLimit(1L, transactionLimit);

        // Assert that the transaction limit was updated correctly
       
        assertEquals(new BigDecimal("12000.00"), result.getDailyLimit());
        assertEquals(new BigDecimal("120000.00"), result.getMonthlyLimit());
    }

    @Test
    public void testUpdateTransactionLimit_LimitNotFound() {
        // Mock repository responses
        when(limitRepository.findById(1L)).thenReturn(Optional.empty());

        // Assert that exception is thrown when transaction limit is not found
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transactionLimitService.updateTransactionLimit(1L, transactionLimit);
        });

        assertEquals("TransactionLimit not found with ID: 1", exception.getMessage());
    }

    @Test
    public void testGetLimitByAccountNumber_Valid() {
        // Mock repository responses
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(account);
        when(limitRepository.findByAccountId(1L)).thenReturn(transactionLimit);

        // Fetch transaction limit for the account
        TransactionLimit result = transactionLimitService.getLimitByAccountNumber("1234567890");

        // Assert that the transaction limit was fetched correctly
       
        assertEquals(new BigDecimal("10000.00"), result.getDailyLimit());
        assertEquals(new BigDecimal("100000.00"), result.getMonthlyLimit());
    }

    @Test
    public void testGetLimitByAccountNumber_AccountNotFound() {
        // Mock repository responses
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(null);

        // Assert that exception is thrown when account is not found
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transactionLimitService.getLimitByAccountNumber("1234567890");
        });

        assertEquals("Account not found with number: 1234567890", exception.getMessage());
    }

    @Test
    public void testGetLimitByAccountNumber_LimitNotSet() {
        // Mock repository responses
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(account);
        when(limitRepository.findByAccountId(1L)).thenReturn(null);

        // Assert that exception is thrown when transaction limit is not set
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transactionLimitService.getLimitByAccountNumber("1234567890");
        });

        assertEquals("TransactionLimit not set for this account.", exception.getMessage());
    }
}
