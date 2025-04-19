package com.example.mb.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.exception.InvalidAccountException;
import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Account;
import com.example.mb.model.Branch;
import com.example.mb.model.Customer;
import com.example.mb.service.AccountService;
import com.example.mb.service.BranchService;
import com.example.mb.service.CustomerService;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private CustomerService customerService;

    // POST method to create or add an account by branchId and customerId
    @PostMapping("/api/account/add/{branchId}/{customerId}")
    public Account addAccount(@PathVariable long branchId, 
    						  @PathVariable long customerId, 
    						  @RequestBody Account account) throws InvalidIdException {
        
        Branch branch = branchService.getById(branchId);      
        Customer customer = customerService.getById(customerId);
        account.setBranch(branch);
        account.setCustomer(customer);       
        return  accountService.createAccount(account);      
    }
    @GetMapping("/api/account/{accountId}")
    public Account getAccountById(@PathVariable long accountId) throws InvalidAccountException {       
        return accountService.getAccountById(accountId);      
        
    }
    
    @GetMapping("/api/accounts/customer/{customerId}")
    public List<Account> getAccountsByCustomerId(@PathVariable long customerId) throws InvalidAccountException {
    	 return accountService.getAccountsByCustomerId(customerId);
        
    }
    
    @PutMapping("/api/account/update/{accountId}")
    public Account updateAccount(@PathVariable long accountId, @RequestBody Account updatedAccount) throws InvalidAccountException {
        Account existingAccount = accountService.getAccountById(accountId);
           existingAccount.setAccountType(updatedAccount.getAccountType());
        existingAccount.setStatus(updatedAccount.getStatus());
        return accountService.updateAccount(existingAccount);
    }
    
    @DeleteMapping("/api/account/deactivate/{accountId}")
    public Account deactivateAccount(@PathVariable long accountId) throws InvalidAccountException {
        Account account = accountService.getAccountById(accountId);       
        account.setStatus("Deactivated");
        return accountService.updateAccount(account);
        
    }
    // Call service to get the account details based on the account number
    @GetMapping("/find/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable String accountNumber) {
      
        return accountService.getAccountByAccountNumber(accountNumber);
    }
    
    // Return balance in the response
    @GetMapping("/api/account/get/balance/{accountNumber}")
    public BigDecimal getBalanceByAccountNumber(@PathVariable String accountNumber) throws InvalidAccountException {
    	 return accountService.getBalance(accountNumber); // Getting balance directly

    }
   
    
}



