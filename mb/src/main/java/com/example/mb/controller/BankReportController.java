package com.example.mb.controller;

import com.example.mb.service.BankReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class BankReportController {

    @Autowired
    private BankReportService bankReportService;

    // Get report for a specific branch
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<?> getBranchReport(@PathVariable Long branchId) {
        Map<String, BigDecimal> report = new HashMap<>();
        
        BigDecimal deposits = bankReportService.getBranchDeposits(branchId);
        BigDecimal withdrawals = bankReportService.getBranchWithdrawals(branchId);
        BigDecimal transfers = bankReportService.getBranchTransfers(branchId);

        // You can add validation if branch ID doesn't exist in future
        report.put("totalDeposits", deposits);
        report.put("totalWithdrawals", withdrawals);
        report.put("totalTransfers", transfers);

        return ResponseEntity.ok(report);
    }

    // Get overall bank report
    @GetMapping("/bank")
    public ResponseEntity<?> getBankReport() {
        Map<String, BigDecimal> report = new HashMap<>();
        report.put("totalDeposits", bankReportService.getTotalBankDeposits());
        report.put("totalWithdrawals", bankReportService.getTotalBankWithdrawals());
        report.put("totalTransfers", bankReportService.getTotalBankTransfers());

        return ResponseEntity.ok(report);
    }
}
