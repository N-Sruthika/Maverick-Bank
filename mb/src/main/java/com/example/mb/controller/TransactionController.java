package com.example.mb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.model.BankTransfer;
import com.example.mb.model.Transaction;
import com.example.mb.model.UPITransaction;
import com.example.mb.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/upi")
    public ResponseEntity<Transaction> makeUPITransaction(@RequestBody UPITransaction upiTransaction) {
        Transaction transaction = transactionService.makeUPITransaction(upiTransaction);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/bank-transfer")
    public ResponseEntity<Transaction> makeBankTransfer(@RequestBody BankTransfer bankTransfer) {
        Transaction transaction = transactionService.makeBankTransfer(bankTransfer);
        return ResponseEntity.ok(transaction);
    }
    @GetMapping("/history/{accountId}")
    public List<Transaction> getTransactionHistory(@PathVariable Long accountId) {
        return transactionService.getTransactionHistory(accountId);
    }
}
