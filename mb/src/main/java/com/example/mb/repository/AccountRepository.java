package com.example.mb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mb.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByCustomerId(long customerId);
	
	Account findByAccountNumber(String accountNumber);

	Account findBalanceByAccountNumber(String accountNumber);

}
