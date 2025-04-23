package com.example.loanmoduleragul.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.loanmoduleragul.model.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    // Custom query to find an applicant by Aadhaar number
    Applicant findByAadhaarNumber(String aadhaarNumber);
}