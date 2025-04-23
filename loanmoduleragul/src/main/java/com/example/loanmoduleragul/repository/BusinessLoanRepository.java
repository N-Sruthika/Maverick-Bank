package com.example.loanmoduleragul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loanmoduleragul.model.BusinessLoan;

@Repository
public interface BusinessLoanRepository extends JpaRepository<BusinessLoan, Long> {
    BusinessLoan findByLoanApplicationId(Long loanApplicationId);
}