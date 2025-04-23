package com.example.loanmoduleragul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.loanmoduleragul.dto.BusinessLoanDto;
import com.example.loanmoduleragul.model.BusinessLoan;
import com.example.loanmoduleragul.service.BusinessLoanService;

@RestController
@RequestMapping("/api/business-loans")
public class BusinessLoanController {

    @Autowired
    private BusinessLoanService businessLoanService;

    @PostMapping
    public ResponseEntity<BusinessLoan> saveBusinessLoan(@RequestBody BusinessLoanRequestDto request) {
        try {
            // Extract loanApplicationId and BusinessLoanDto from the request object
            Long loanApplicationId = request.getLoanApplicationId();
            BusinessLoanDto businessLoanDto = request.getBusinessLoanDto();

            // Call the service method with both arguments
            BusinessLoan businessLoan = businessLoanService.saveBusinessLoan(loanApplicationId, businessLoanDto);
            return ResponseEntity.ok(businessLoan);
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Handle internal server errors
        }
    }
}