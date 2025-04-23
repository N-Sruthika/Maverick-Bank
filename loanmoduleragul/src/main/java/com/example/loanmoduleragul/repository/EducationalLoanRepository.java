package com.example.loanmoduleragul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loanmoduleragul.model.EducationalLoan;

public interface EducationalLoanRepository extends JpaRepository<EducationalLoan, Long> {
    // Custom query to find educational loans by loan application ID
    EducationalLoan findByLoanApplicationId(Long loanApplicationId);
}