package com.example.mb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mb.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByFromAccountId(Long accountId);
    // Custom query methods can be added here if needed

	List<Transaction> findByFromAccount_AccountNumber(String accountNumber);
}
