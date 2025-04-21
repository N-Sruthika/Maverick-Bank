package com.example.mb.controller;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.TransactionLimit;
import com.example.mb.service.TransactionLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction-limit")
public class TransactionLimitController {

    @Autowired
    private TransactionLimitService transactionLimitService;

    @PostMapping("/add/{accountId}")
    public TransactionLimit addLimit(@PathVariable Long accountId, @RequestBody TransactionLimit request) {
        return transactionLimitService.addTransactionLimit(accountId, request);
    }

    @PutMapping("/update/{id}")
    public TransactionLimit updateLimit(@PathVariable Long id, @RequestBody TransactionLimit request) throws InvalidIdException {
        return transactionLimitService.updateTransactionLimit(id, request);
    }

    @GetMapping("/account/{accountNumber}")
    public TransactionLimit getLimitByAccountNumber(@PathVariable String accountNumber) {
        return transactionLimitService.getLimitByAccountNumber(accountNumber);
    }
}
