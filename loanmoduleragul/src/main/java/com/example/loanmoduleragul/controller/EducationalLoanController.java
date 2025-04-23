package com.example.loanmoduleragul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanmoduleragul.dto.EducationalLoanDto;
import com.example.loanmoduleragul.model.EducationalLoan;
import com.example.loanmoduleragul.service.EducationalLoanService;

@RestController
@RequestMapping("/api/educational-loans")
public class EducationalLoanController {

    @Autowired
    private EducationalLoanService educationalLoanService;

    @PostMapping
    public ResponseEntity<EducationalLoan> saveEducationalLoan(@RequestBody EducationalLoanDto request) {
        try {
            EducationalLoan educationalLoan = educationalLoanService.saveEducationalLoan(request);
            return ResponseEntity.ok(educationalLoan);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Handle internal server errors
        }
    }
}