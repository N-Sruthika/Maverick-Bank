package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Account;
import com.example.mb.model.Branch;
import com.example.mb.model.Customer;
import com.example.mb.model.TransactionLimit;
import com.example.mb.repository.AccountRepository;
import com.example.mb.repository.TransactionLimitRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
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
    public void init() {
    	Branch branch = new Branch(1L, "Branch1", "IFSC001"); // Adjust as needed
    	Customer customer = new Customer();
    // Create Account instance using constructor
        account = new Account("ACC12345", "IFSC001", "SAVINGS", BigDecimal.valueOf(5000), "ACTIVE", branch, customer);

        // Create TransactionLimit instance using constructor
        transactionLimit = new TransactionLimit(1L, BigDecimal.valueOf(10000), BigDecimal.valueOf(50000), account);
     }

    @Test
    public void addTransactionLimitTest() {
        // Arrange
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(limitRepository.save(transactionLimit)).thenReturn(transactionLimit);

        // Act
        TransactionLimit result = transactionLimitService.addTransactionLimit(1L, transactionLimit);

        // Assert
        assertEquals(transactionLimit, result);
        verify(accountRepository, times(1)).findById(1L);
        verify(limitRepository, times(1)).save(transactionLimit);
    }

    

    @Test
    public void updateTransactionLimitTest() throws InvalidIdException {
        // Arrange
        TransactionLimit updatedLimit = new TransactionLimit(1L, BigDecimal.valueOf(15000), BigDecimal.valueOf(70000), account);

        when(limitRepository.findById(1L)).thenReturn(Optional.of(transactionLimit));
        when(limitRepository.save(transactionLimit)).thenReturn(transactionLimit);

        // Act
        TransactionLimit result = transactionLimitService.updateTransactionLimit(1L, updatedLimit);

        // Assert
        assertEquals(updatedLimit.getDailyLimit(), result.getDailyLimit());
        assertEquals(updatedLimit.getMonthlyLimit(), result.getMonthlyLimit());
        verify(limitRepository, times(1)).findById(1L);
        verify(limitRepository, times(1)).save(transactionLimit);
    }

   

    @Test
    public void getLimitByAccountNumberTest() {
        // Arrange
        when(accountRepository.findByAccountNumber("ACC12345")).thenReturn(account);
        when(limitRepository.findByAccount(account)).thenReturn(transactionLimit);

        // Act
        TransactionLimit result = transactionLimitService.getLimitByAccountNumber("ACC12345");

        // Assert
        assertEquals(transactionLimit, result);
        verify(accountRepository, times(1)).findByAccountNumber("ACC12345");
        verify(limitRepository, times(1)).findByAccount(account);
    }

   
}
