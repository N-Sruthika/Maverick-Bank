package com.example.mb.repository;

import com.example.mb.model.UPITransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UPITransactionRepository extends JpaRepository<UPITransaction, Long> {
    // Custom query methods can be added here if needed
}
