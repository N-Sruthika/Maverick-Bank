package com.example.mb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.dto.BankTransferRequest;
import com.example.mb.dto.UPITransactionRequest;
import com.example.mb.exception.InsufficientBalanceException;
import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.Transaction;
import com.example.mb.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Make Bank Transfer
    @PostMapping("/bank-transfer/{accountNumber}")
    public ResponseEntity<Transaction> makeBankTransfer(
            @RequestBody BankTransferRequest request, 
            @PathVariable String accountNumber) throws InvalidAccountException, InsufficientBalanceException {
        
        // Passing accountNumber to the service
        Transaction transaction = transactionService.makeBankTransfer(request, accountNumber);
        return ResponseEntity.ok(transaction);
    }

    // Make UPI Transfer
    @PostMapping("/upi-transfer/{accountNumber}")
    public ResponseEntity<Transaction> makeUPITransfer(
            @RequestBody UPITransactionRequest request, 
            @PathVariable String accountNumber) throws InvalidAccountException, InsufficientBalanceException {
        
        // Passing accountNumber to the service
        Transaction transaction = transactionService.makeUPITransfer(request, accountNumber);
        return ResponseEntity.ok(transaction);
    }

    // Get Transaction History
    @GetMapping("/history/{accountNumber}")
    public ResponseEntity<?> getTransactionHistory(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(accountNumber));
    }
}
