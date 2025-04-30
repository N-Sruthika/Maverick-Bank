package com.example.mb.repository;

import com.example.mb.model.BankTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransferRepository extends JpaRepository<BankTransfer, Long> {
    // Custom query methods can be added here if needed
}
