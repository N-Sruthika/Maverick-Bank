package com.example.loanmoduleragul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanmoduleragul.dto.IncomeDetailsDto;
import com.example.loanmoduleragul.model.IncomeDetails;
import com.example.loanmoduleragul.model.LoanApplication;
import com.example.loanmoduleragul.repository.IncomeDetailsRepository;
import com.example.loanmoduleragul.repository.LoanApplicationRepository;
@Service
public class IncomeDetailsService {

    @Autowired
    private IncomeDetailsRepository incomeDetailsRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    public void saveIncomeDetails(Long loanApplicationId, IncomeDetailsDto dto) {
        // Fetch the existing LoanApplication from the database
        LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
                .orElseThrow(() -> new RuntimeException("Loan Application not found"));

        // Create and save the IncomeDetails
        IncomeDetails incomeDetails = new IncomeDetails();
        incomeDetails.setEmploymentType(dto.getEmploymentType());
        incomeDetails.setAnnualIncome(dto.getAnnualIncome());
        incomeDetails.setEmployerName(dto.getEmployerName());
        incomeDetails.setDesignation(dto.getDesignation());
        incomeDetails.setEmployeeId(dto.getEmployeeId());
        incomeDetails.setForm16Url(dto.getForm16Url());
        incomeDetails.setSalarySlipUrl(dto.getSalarySlipUrl());
        incomeDetails.setSalaryBankStatementUrl(dto.getSalaryBankStatementUrl());
        incomeDetails.setProfession(dto.getProfession());
        incomeDetails.setBusinessRegistrationProofUrl(dto.getBusinessRegistrationProofUrl());
        incomeDetails.setSelfEmployedBankStatementUrl(dto.getSelfEmployedBankStatementUrl());
        incomeDetails.setSelfEmployedItrUrl(dto.getSelfEmployedItrUrl());
        incomeDetails.setBusinessName(dto.getBusinessName());
        incomeDetails.setBusinessType(dto.getBusinessType());
        incomeDetails.setGstNumber(dto.getGstNumber());
        incomeDetails.setBusinessStartYear(dto.getBusinessStartYear());
        incomeDetails.setAuditedFinancialsUrl(dto.getAuditedFinancialsUrl());
        incomeDetails.setBusinessBankStatementUrl(dto.getBusinessBankStatementUrl());
        incomeDetails.setLoanApplication(loanApplication); // Associate with LoanApplication
        incomeDetailsRepository.save(incomeDetails);
    }
}