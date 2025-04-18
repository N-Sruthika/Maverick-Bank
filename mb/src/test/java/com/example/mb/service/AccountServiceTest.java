package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

    private Account account1;
    private Account account2;
    private Customer customer;
    private Branch branch;

    @BeforeEach
    public void setup() {
        branch = new Branch();
        branch.setId(1L);
        branch.setBranchName("Main Branch");

        customer = new Customer();
        customer.setId(100L);
        customer.setName("John Doe");

        account1 = new Account();
        account1.setId(1L);
        account1.setAccountNumber("1234567890");
        account1.setBalance(new BigDecimal("5000.00"));
        account1.setCustomer(customer);
        account1.setBranch(branch);

        account2 = new Account();
        account2.setId(2L);
        account2.setAccountNumber("0987654321");
        account2.setBalance(new BigDecimal("10000.00"));
        account2.setCustomer(customer);
        account2.setBranch(branch);
    }
    @Test
    public void testCreateAccount() {
        when(accountRepository.save(account1)).thenReturn(account1);

        Account savedAccount = accountService.createAccount(account1);

        assertEquals("1234567890", savedAccount.getAccountNumber());
        verify(accountRepository, times(1)).save(account1);
    }
    

    @Test
    public void testGetBalance_ValidAccount() throws InvalidAccountException {
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(account1);

        BigDecimal balance = accountService.getBalance("1234567890");

        assertEquals(new BigDecimal("5000.00"), balance);
        verify(accountRepository, times(1)).findByAccountNumber("1234567890");
    }

//    @Test
//    public void testGetAccountsByCustomerId() {
//        when(accountRepository.findByCustomerId(100L)).thenReturn(Arrays.asList(account1, account2));
//
//        List<Account> accounts = accountService.getAccountsByCustomerId(100L);
//        assertEquals("1234567890", accounts.get(0).getAccountNumber());
//       
//        verify(accountRepository, times(1)).findByCustomerId(100L);
//    }
    @Test
    public void testUpdateAccount() {
        when(accountRepository.save(account1)).thenReturn(account1);

        Account updatedAccount = accountService.updateAccount(account1);

        assertEquals("1234567890", updatedAccount.getAccountNumber());
        verify(accountRepository, times(1)).save(account1);
    }


    @Test
    public void testGetAccountById() throws InvalidAccountException {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));

        Account result = accountService.getAccountById(1L);

        assertEquals("1234567890", result.getAccountNumber());
        verify(accountRepository, times(1)).findById(1L);
    }
    @Test
    public void testGetAccountByAccountNumber_Valid() {
        when(accountRepository.findByAccountNumber("1234567890")).thenReturn(account1);

        Account result = accountService.getAccountByAccountNumber("1234567890");

        assertEquals("1234567890", result.getAccountNumber());
        verify(accountRepository, times(1)).findByAccountNumber("1234567890");
    }

    @Test
    public void testGetAccountByAccountNumber_Invalid() {
        when(accountRepository.findByAccountNumber("9999999999")).thenReturn(null);

        try {
            accountService.getAccountByAccountNumber("9999999999");
        } catch (RuntimeException e) {
            assertEquals("Account not found for account number: 9999999999", e.getMessage());
            verify(accountRepository, times(1)).findByAccountNumber("9999999999");
        }
    }


    
}
