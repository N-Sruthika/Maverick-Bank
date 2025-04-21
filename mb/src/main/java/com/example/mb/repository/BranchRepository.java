package com.example.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mb.model.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {
    // Custom query methods can be added here if needed
}

