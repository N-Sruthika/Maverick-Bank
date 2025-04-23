package com.example.loanmoduleragul.dto;

import java.time.LocalDate;

import com.example.loanmoduleragul.enums.LoanStatus;
import com.example.loanmoduleragul.enums.LoanType;

public class LoanApplicationRequestDto {

    private ApplicantDto applicant;
    private LoanApplicationDto loanApplication;
    private EducationalLoanDto educationalLoan;
    private BusinessLoanDto businessLoan;
    private IncomeDetailsDto incomeDetails;

    // Getters and Setters
    public ApplicantDto getApplicant() {
        return applicant;
    }

    public void setApplicant(ApplicantDto applicant) {
        this.applicant = applicant;
    }

    public LoanApplicationDto getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplicationDto loanApplication) {
        this.loanApplication = loanApplication;
    }

    public EducationalLoanDto getEducationalLoan() {
        return educationalLoan;
    }

    public void setEducationalLoan(EducationalLoanDto educationalLoan) {
        this.educationalLoan = educationalLoan;
    }

    public BusinessLoanDto getBusinessLoan() {
        return businessLoan;
    }

    public void setBusinessLoan(BusinessLoanDto businessLoan) {
        this.businessLoan = businessLoan;
    }

    public IncomeDetailsDto getIncomeDetails() {
        return incomeDetails;
    }

    public void setIncomeDetails(IncomeDetailsDto incomeDetails) {
        this.incomeDetails = incomeDetails;
    }
}