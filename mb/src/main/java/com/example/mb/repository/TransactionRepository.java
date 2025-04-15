package com.example.mb.repository;

import com.example.mb.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // By Branch
    BigDecimal getTotalDepositsByBranch(@Param("branchId") Long branchId);

    BigDecimal getTotalWithdrawalsByBranch(@Param("branchId") Long branchId);

    BigDecimal getTotalTransfersByBranch(@Param("branchId") Long branchId);

    // For entire bank
    BigDecimal getTotalDeposits();

    BigDecimal getTotalWithdrawals();

    BigDecimal getTotalTransfers();
}
