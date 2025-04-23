package com.example.loanmoduleragul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.loanmoduleragul.model.IncomeDetails;

@Repository
public interface IncomeDetailsRepository extends JpaRepository<IncomeDetails, Long> {
    IncomeDetails findByLoanApplicationId(Long loanApplicationId);
}