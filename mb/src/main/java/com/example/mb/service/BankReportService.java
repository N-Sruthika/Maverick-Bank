package com.example.mb.service;

import com.example.mb.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankReportService {

    @Autowired
    private TransactionRepository transactionRepository;

    public BigDecimal getBranchDeposits(Long branchId) {
        return transactionRepository.getTotalDepositsByBranch(branchId);
    }

    public BigDecimal getBranchWithdrawals(Long branchId) {
        return transactionRepository.getTotalWithdrawalsByBranch(branchId);
    }

    public BigDecimal getBranchTransfers(Long branchId) {
        return transactionRepository.getTotalTransfersByBranch(branchId);
    }

    public BigDecimal getTotalBankDeposits() {
        return transactionRepository.getTotalDeposits();
    }

    public BigDecimal getTotalBankWithdrawals() {
        return transactionRepository.getTotalWithdrawals();
    }

    public BigDecimal getTotalBankTransfers() {
        return transactionRepository.getTotalTransfers();
    }
}
