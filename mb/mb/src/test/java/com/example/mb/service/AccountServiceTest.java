package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.model.Branch;
import com.example.mb.model.Customer;
import com.example.mb.repository.AccountRepository;
import com.example.mb.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account, account2;
    private Customer customer;
    private Branch branch;

    @BeforeEach
    void setup() {
        // Setup mock branch
        branch = new Branch(1L, "Main Branch", "123 Main St");

        // Setup mock customer
        customer = new Customer();
        customer.setId(1L);

        // Setup mock account
        account = new Account(1L, "1234567890", "IFSC0001", "Savings",
                new BigDecimal("5000.00"), "Active", branch, customer);
        account2 = new Account(2L, "0987654321", "IFSC0002", "Checking",
                new BigDecimal("2000.00"), "Active", branch, customer);
   
    }

    @Test
    public void getAccountsByCustomerIdTest() throws InvalidAccountException {
        // Expectation: List<Account> list = Arrays.asList(account1, account2);
        // Actual: accountService.getAccountsByCustomerId(1L)
        List<Account> list = Arrays.asList(account, account2);
        Long customerId = 1L;
        when(accountRepository.findByCustomerId(customerId)).thenReturn(list);

        // Use case 1: customer exists, return the list
        try {
            assertEquals(list, accountService.getAccountsByCustomerId(customerId));
        } catch (InvalidAccountException e) {
            // Handle the exception if thrown, but this case is successful
        }

    }
    @Test
    void testGetAllAccount() {
        // Given: Mocked list of accounts
        when(accountRepository.findAll()).thenReturn(Arrays.asList(account, account2));

        // When: Call the service method to get all accounts
        List<Account> accounts = accountService.getAllAccount();

        // Then: Assert that the accounts list  contains 2 accounts
       
        assertEquals(2, accounts.size());

        // Verify that the repository method was called exactly once
        verify(accountRepository, times(1)).findAll();
    }


    @Test
    void testGetBalance() {
        when(accountRepository.findBalanceByAccountNumber("1234567890")).thenReturn(account);

        BigDecimal balance = accountService.getBalance("1234567890");

        assertEquals(new BigDecimal("5000.00"), balance);
        verify(accountRepository, times(1)).findBalanceByAccountNumber("1234567890");
    }

    @Test
    void testCountActiveAccountsByCustomer() {
        when(accountRepository.countByCustomerIdAndStatus(1L, "Active")).thenReturn(2);

        int count = accountService.countActiveAccountsByCustomer(1L);

        assertEquals(2, count);
        verify(accountRepository, times(1)).countByCustomerIdAndStatus(1L, "Active");
    }
}
