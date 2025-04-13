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
        // Fetch the branch using the branchId
        Branch branch = branchService.getById(branchId);
        if (branch == null) {
            throw new IllegalArgumentException("Invalid branchId: " + branchId); // Handle invalid branch
        }
        Customer customer = customerService.getById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Invalid customerId: " + customerId); // Handle invalid customer
        }

        // Set the branch and customer for the account
        account.setBranch(branch);
        account.setCustomer(customer);

        // If balance is not provided, set default value
        if (account.getBalance() == null) {
            account.setBalance(BigDecimal.ZERO); // Default balance set to 0
        }

        // Save the account using the service
        return  accountService.createAccount(account);

       
    }
    @GetMapping("/api/account/{accountId}")
    public Account getAccountById(@PathVariable long accountId) throws InvalidAccountException {
        // Fetch the account by ID
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found with ID: " + accountId);
        }
        return account;
    }
    
    @GetMapping("/api/accounts/customer/{customerId}")
    public List<Account> getAccountsByCustomerId(@PathVariable long customerId) {
        // Fetch all accounts for a customer by customerId
        List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("No accounts found for customer ID: " + customerId);
        }
        return accounts;
    }
    @PutMapping("/api/account/update/{accountId}")
    public Account updateAccount(@PathVariable long accountId, @RequestBody Account updatedAccount) throws InvalidAccountException {
        // Fetch the existing account by ID
        Account existingAccount = accountService.getAccountById(accountId);
        if (existingAccount == null) {
            throw new IllegalArgumentException("Account not found with ID: " + accountId);
        }

        // Update account details
        existingAccount.setAccountType(updatedAccount.getAccountType());
        existingAccount.setBalance(updatedAccount.getBalance());
        existingAccount.setStatus(updatedAccount.getStatus());
        existingAccount.setIfscCode(updatedAccount.getIfscCode());

        // Save updated account and return it
        return accountService.updateAccount(existingAccount);
    }
    @DeleteMapping("/api/account/deactivate/{accountId}")
    public String deactivateAccount(@PathVariable long accountId) throws InvalidAccountException {
        // Fetch the account by ID
        Account account = accountService.getAccountById(accountId);
        if (account == null) {
            throw new IllegalArgumentException("Account not found with ID: " + accountId);
        }

        // Deactivate the account by changing its status
        account.setStatus("Deactivated");
        accountService.updateAccount(account);

        return "Account deactivated successfully.";
    }
    @GetMapping("/find/{accountNumber}")
    public Account getAccountByAccountNumber(@PathVariable String accountNumber) {
        // Call service to get the account details based on the account number
        return accountService.getAccountByAccountNumber(accountNumber);
    }




}
