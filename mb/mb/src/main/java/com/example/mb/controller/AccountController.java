package com.example.mb.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.service.AccountService;
import com.example.mb.service.BranchService;
import com.example.mb.service.CustomerService;

@RestController
//@CrossOrigin(origins = {"http://localhost:5173"})

public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private CustomerService customerService;

    
    @GetMapping("/api/accounts/customer/{customerId}")
    public List<Account> getAccountsByCustomerId(@PathVariable long customerId) throws InvalidAccountException {
    	 return accountService.getAccountsByCustomerId(customerId);
        
    }
    //redux
    @GetMapping("/api/accounts")
    public List<Account> getAll() throws InvalidAccountException {
    	 return accountService.getAllAccount();
        
    }
  
    // Return balance in the response--
    @GetMapping("/api/account/get/balance/{accountNumber}")
    public BigDecimal getBalanceByAccountNumber(@PathVariable String accountNumber) throws InvalidAccountException {
    	 return accountService.getBalance(accountNumber); // Getting balance directly

    }

 // GET method to get number of active accounts for a specific customer--
    @GetMapping("/api/accounts/active/count/{customerId}")
    public int getActiveAccountsCountByCustomer(@PathVariable long customerId) {
        return accountService.countActiveAccountsByCustomer(customerId);
    }  
    

}



