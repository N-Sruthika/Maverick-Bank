package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.model.Branch;
import com.example.mb.model.Customer;
import com.example.mb.repository.AccountRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    private Customer c1, c2;
    private Account a1, a2, a3;
    private Branch branch;

    @BeforeEach
    public void init() {
        // Initialize Branch instance
        branch = new Branch("Main Branch", "123 Main St");
        branch.setId(1L);  // Manually set ID for testing

       
        // Initialize Customer instances
        c1 = new Customer(1L, "Customer One", "customer1", "111111", "customer1@example.com", "123 Street", LocalDate.of(1990, 1, 1), 
                          "Bachelors", "Engineer", "Single", "ABCDE1234F", "123456789012", branch);
        c2 = new Customer(2L, "Customer Two", "customer2", "222222", "customer2@example.com", "456 Street", LocalDate.of(1985, 5, 20), 
                          "Masters", "Doctor", "Married", "FGHIJ5678G", "234567890123", branch);

        // Initialize Account instances
        a1 = new Account(1L, "ACC123", "IFSC001", "SAVINGS", new BigDecimal("1000.00"), "ACTIVE", branch, c1);
        a2 = new Account(2L, "ACC456", "IFSC002", "CURRENT", new BigDecimal("2000.00"), "ACTIVE", branch, c1);
        a3 = new Account(3L, "ACC789", "IFSC003", "SAVINGS", new BigDecimal("3000.00"), "INACTIVE", branch, c2);
    }

    @Test
    public void createAccountTest() {
        when(accountRepository.save(a1)).thenReturn(a1);
        assertEquals(a1, accountService.createAccount(a1));
        verify(accountRepository, times(1)).save(a1);
    }

    @Test
    public void getAccountByIdTest_validId() throws InvalidAccountException {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(a1));
        assertEquals(a1, accountService.getAccountById(1L));
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    public void getAccountByIdTest_invalidId() {
        when(accountRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(InvalidAccountException.class, () -> accountService.getAccountById(99L));
        verify(accountRepository, times(1)).findById(99L);
    }

    @Test
    public void getAccountsByCustomerIdTest_validId() throws InvalidAccountException {
        List<Account> accounts = Arrays.asList(a1, a2);
        when(accountRepository.findByCustomerId(1L)).thenReturn(accounts);
        assertEquals(accounts, accountService.getAccountsByCustomerId(1L));
        verify(accountRepository, times(1)).findByCustomerId(1L);
    }

    @Test
    public void getAccountsByCustomerIdTest_invalidId() {
        when(accountRepository.findByCustomerId(5L)).thenReturn(Arrays.asList());
        assertThrows(InvalidAccountException.class, () -> accountService.getAccountsByCustomerId(5L));
        verify(accountRepository, times(1)).findByCustomerId(5L);
    }

    @Test
    public void updateAccountTest() {
        when(accountRepository.save(a3)).thenReturn(a3);
        assertEquals(a3, accountService.updateAccount(a3));
        verify(accountRepository, times(1)).save(a3);
    }

    @Test
    public void getAccountByAccountNumberTest() {
        when(accountRepository.findByAccountNumber("ACC123")).thenReturn(a1);
        assertEquals(a1, accountService.getAccountByAccountNumber("ACC123"));
        verify(accountRepository, times(1)).findByAccountNumber("ACC123");
    }

    @Test
    public void getBalanceTest() {
        when(accountRepository.findBalanceByAccountNumber("ACC123")).thenReturn(a1);
        assertEquals(new BigDecimal("1000.00"), accountService.getBalance("ACC123"));
        verify(accountRepository, times(1)).findBalanceByAccountNumber("ACC123");
    }
}
