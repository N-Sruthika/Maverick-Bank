package com.example.mb.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.repository.AccountRepository;
import com.example.mb.repository.CustomerRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;
    Logger logger = LoggerFactory.getLogger("AccountService");
    public Account createAccount(Account account) {
        logger.info("Creating a new account for customer ID: {}", account.getCustomer().getId());
        return accountRepository.save(account);
    }
    
    
    
    public Account getAccountById(long accountId) throws InvalidAccountException {
        Optional<Account> accountOpt = accountRepository.findById(accountId);
        if (accountOpt.isEmpty()) {
            logger.warn("Account not found for ID: {}", accountId);
            throw new InvalidAccountException("Account not found for the given ID.");
        }
        return accountOpt.get();
    }
    

    public List<Account> getAccountsByCustomerId(long customerId) throws InvalidAccountException {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        if (accounts.isEmpty()) {
            logger.warn("No accounts found for customer ID: {}", customerId);
            throw new InvalidAccountException("No accounts found for the given customer ID.");
        }
        logger.info("Accounts retrieved for customer ID: {}", customerId);
        return accounts;
    }
    

    public Account updateAccount(Account account) {
        logger.info("Updating account with ID: {}", account.getId());
        return accountRepository.save(account);
    }



	public Account getAccountByAccountNumber(String accountNumber) {
	
		return accountRepository.findByAccountNumber(accountNumber);
	}

	public BigDecimal getBalance(String accountNumber) {
		
		Account account= accountRepository.findBalanceByAccountNumber(accountNumber);
		return account.getBalance();
	}



	public int countActiveAccountsByCustomer(long customerId) {
	    return accountRepository.countByCustomerIdAndStatus(customerId, "Active");
	}



	public BigDecimal getBalanceById(int cid) {
		
		Account account= accountRepository.findBalanceByCustomerId(cid);
		return account.getBalance();
	}



	public BigDecimal getBalanceByUsername(String username) {
	    return null;  // Return the balance
	}



	public List<Account> getAllAccount() {
		
		return accountRepository.findAll();
	}




	 

}
