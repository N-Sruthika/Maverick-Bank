package com.example.mb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.exception.InsufficientBalanceException;
import com.example.mb.exception.InvalidAccountException;
import com.example.mb.model.BankTransfer;
import com.example.mb.model.Transaction;
import com.example.mb.model.UPITransaction;
import com.example.mb.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = {"http://localhost:5173"})
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Make Bank Transfer
    @PostMapping("/bank-transfer/{accountNumber}")
    public Transaction makeBankTransfer(
            @RequestBody BankTransfer bankTransfer, 
            @PathVariable String accountNumber) throws InvalidAccountException, InsufficientBalanceException {
        return transactionService.makeBankTransfer(bankTransfer, accountNumber);
    }

    // Make UPI Transfer
    @PostMapping("/upi-transfer/{accountNumber}")
    public Transaction makeUPITransfer(
            @RequestBody UPITransaction upiTransaction, 
            @PathVariable String accountNumber) throws InvalidAccountException, InsufficientBalanceException {
        return transactionService.makeUPITransfer(upiTransaction, accountNumber);
    }

  
    @GetMapping("/customer/{customerId}")
    public List<Transaction> getTransactionsByCustomerId(@PathVariable Long customerId) {
        return transactionService.getTransactionsByCustomerId(customerId);
    }
    @GetMapping("/customer/history/{customerId}")
    public Page<Transaction> getPaginatedTransactionHistoryByCustomerId(
            @PathVariable Long customerId,
            Pageable pageable) {
        return transactionService.getPaginatedTransactionHistoryByCustomerId(customerId, pageable);
    }
    

}
