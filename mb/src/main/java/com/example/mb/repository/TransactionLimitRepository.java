package com.example.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mb.model.Account;
import com.example.mb.model.TransactionLimit;

public interface TransactionLimitRepository extends JpaRepository<TransactionLimit, Long> {
     // Find transaction limit by account ID

	TransactionLimit findByAccountId(Account account);
	TransactionLimit findByAccount(Account account);

	
}
