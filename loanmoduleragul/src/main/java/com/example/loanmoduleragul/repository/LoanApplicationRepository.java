package com.example.loanmoduleragul.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loanmoduleragul.enums.LoanType;
import com.example.loanmoduleragul.model.LoanApplication;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
    // Custom query to find loan applications by applicant ID
    List<LoanApplication> findByApplicantId(Long applicantId);

    // Custom query to find loan applications by loan type
    List<LoanApplication> findByLoanType(LoanType loanType);
}