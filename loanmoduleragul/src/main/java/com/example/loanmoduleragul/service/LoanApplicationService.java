package com.example.loanmoduleragul.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.loanmoduleragul.dto.LoanApplicationRequestDto;
import com.example.loanmoduleragul.enums.LoanType;
import com.example.loanmoduleragul.model.EducationalLoan;
import com.example.loanmoduleragul.model.LoanApplication;
import com.example.loanmoduleragul.repository.EducationalLoanRepository;
import com.example.loanmoduleragul.repository.LoanApplicationRepository;

@Service
public class LoanApplicationService {

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private EducationalLoanRepository educationalLoanRepository;

    public LoanApplication createLoanApplication(LoanApplicationRequestDto request) {
        // Step 1: Save Loan Application
        LoanApplication loanApplication = new LoanApplication();
        loanApplication.setLoanType(request.getLoanApplication().getLoanType());
        loanApplication.setCibilScore(request.getLoanApplication().getCibilScore());
        loanApplication.setMinLoanAmount(request.getLoanApplication().getMinLoanAmount());
        loanApplication.setMaxLoanAmount(request.getLoanApplication().getMaxLoanAmount());
        loanApplication.setMinTenure(request.getLoanApplication().getMinTenure());
        loanApplication.setMaxTenure(request.getLoanApplication().getMaxTenure());
        loanApplication.setInterestRate(request.getLoanApplication().getInterestRate());
        LoanApplication savedLoanApplication = loanApplicationRepository.save(loanApplication);

        // Step 2: Save Educational Loan
        if (request.getLoanApplication().getLoanType() == LoanType.EDUCATIONAL) {
            EducationalLoan educationalLoan = new EducationalLoan();
            educationalLoan.setMarkSheet10(request.getEducationalLoan().getMarkSheet10());
            educationalLoan.setMarkSheet12(request.getEducationalLoan().getMarkSheet12());
            educationalLoan.setCertificateUG(request.getEducationalLoan().getCertificateUG());
            educationalLoan.setAdmissionLetter(request.getEducationalLoan().getAdmissionLetter());
            educationalLoan.setFeeStructure(request.getEducationalLoan().getFeeStructure());
            educationalLoan.setEntranceExamScorecard(request.getEducationalLoan().getEntranceExamScorecard());
            educationalLoan.setLoanApplication(savedLoanApplication); // Associate with saved LoanApplication
            educationalLoanRepository.save(educationalLoan);
        }

        return savedLoanApplication;
    }
}