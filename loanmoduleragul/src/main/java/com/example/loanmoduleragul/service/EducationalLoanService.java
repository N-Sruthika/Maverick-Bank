package com.example.loanmoduleragul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanmoduleragul.dto.EducationalLoanDto;
import com.example.loanmoduleragul.model.EducationalLoan;
import com.example.loanmoduleragul.model.LoanApplication;
import com.example.loanmoduleragul.repository.EducationalLoanRepository;
import com.example.loanmoduleragul.repository.LoanApplicationRepository;

@Service
public class EducationalLoanService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;
    private EducationalLoanRepository educationalLoanRepository;

    public void saveEducationalLoan(Long loanApplicationId, EducationalLoanDto dto) {
        // Fetch the existing LoanApplication from the database
        LoanApplication loanApplication = loanApplicationRepository.findById(loanApplicationId)
                .orElseThrow(() -> new RuntimeException("Loan Application not found"));

        // Create and save the EducationalLoan
        EducationalLoan educationalLoan = new EducationalLoan();
        educationalLoan.setMarkSheet10(dto.getMarkSheet10());
        educationalLoan.setMarkSheet12(dto.getMarkSheet12());
        educationalLoan.setCertificateUG(dto.getCertificateUG());
        educationalLoan.setAdmissionLetter(dto.getAdmissionLetter());
        educationalLoan.setFeeStructure(dto.getFeeStructure());
        educationalLoan.setEntranceExamScorecard(dto.getEntranceExamScorecard());
        educationalLoan.setLoanApplication(loanApplication); // Associate with the fetched LoanApplication
        educationalLoanRepository.save(educationalLoan);
    }
}