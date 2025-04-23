package com.example.loanmoduleragul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanmoduleragul.dto.BusinessLoanDto;
import com.example.loanmoduleragul.model.BusinessLoan;
import com.example.loanmoduleragul.model.LoanApplication;
import com.example.loanmoduleragul.repository.BusinessLoanRepository;
import com.example.loanmoduleragul.repository.LoanApplicationRepository;

@Service
public class BusinessLoanService {

    @Autowired
    private BusinessLoanRepository businessLoanRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    public void saveBusinessLoan(Long loanApplicationId, BusinessLoanDto dto) {
        // Fetch the existing LoanApplication from the database
        LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
                .orElseThrow(() -> new RuntimeException("Loan Application not found"));

        // Create and save the BusinessLoan
        BusinessLoan businessLoan = new BusinessLoan();
        businessLoan.setBusinessRegistrationProofUrl(dto.getBusinessRegistrationProofUrl());
        businessLoan.setGstNumber(dto.getGstNumber());
        businessLoan.setProfitAndLossStatementUrl(dto.getProfitAndLossStatementUrl());
        businessLoan.setBalanceSheetUrl(dto.getBalanceSheetUrl());
        businessLoan.setBusinessPlanUrl(dto.getBusinessPlanUrl());
        businessLoan.setLoanApplication(loanApplication); // Associate with LoanApplication
        businessLoanRepository.save(businessLoan);
    }
}