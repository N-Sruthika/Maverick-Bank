package com.example.loanmoduleragul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanmoduleragul.dto.LoanApplicationRequestDto;
import com.example.loanmoduleragul.model.LoanApplication;
import com.example.loanmoduleragul.service.LoanApplicationService;
@RestController
@RequestMapping("/api/loan-applications")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<LoanApplication> createLoanApplication(@RequestBody LoanApplicationRequestDto request) {
        try {
            LoanApplication loanApplication = loanApplicationService.createLoanApplication(request);
            return ResponseEntity.ok(loanApplication);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}