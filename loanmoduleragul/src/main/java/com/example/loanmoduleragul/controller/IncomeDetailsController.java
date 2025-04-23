package com.example.loanmoduleragul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanmoduleragul.dto.IncomeDetailsDto;
import com.example.loanmoduleragul.model.IncomeDetails;
import com.example.loanmoduleragul.service.IncomeDetailsService;

@RestController
@RequestMapping("/api/income-details")
public class IncomeDetailsController {

    @Autowired
    private IncomeDetailsService incomeDetailsService;

    @PostMapping
    public ResponseEntity<IncomeDetails> saveIncomeDetails(@RequestBody IncomeDetailsDto request) {
        try {
            IncomeDetails incomeDetails = incomeDetailsService.saveIncomeDetails(request);
            return ResponseEntity.ok(incomeDetails);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Handle internal server errors
        }
    }
}