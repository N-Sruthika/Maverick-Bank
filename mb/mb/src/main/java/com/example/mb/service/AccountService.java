package com.example.mb.service;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Account;
import com.example.mb.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
      Logger logger = LoggerFactory.getLogger("AccountService");
    
   

    public List<Account> getAccountsByCustomerId(long customerId) throws InvalidAccountException {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);
        if (accounts.isEmpty()) {
            logger.warn("No accounts found for customer ID: {}", customerId);
            throw new InvalidAccountException("No accounts found for the given customer ID.");
        }
        logger.info("Accounts retrieved for customer ID: {}", customerId);
        return accounts;
    }
    
    
    public List<Account> getAllAccount() {
		
		return accountRepository.findAll();
	}


	public BigDecimal getBalance(String accountNumber) {
		
		Account account= accountRepository.findBalanceByAccountNumber(accountNumber);
		return account.getBalance();
	}



	public int countActiveAccountsByCustomer(long customerId) {
	    return accountRepository.countByCustomerIdAndStatus(customerId, "Active");
	}

	 

}
