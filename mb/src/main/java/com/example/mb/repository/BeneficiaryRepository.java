package com.example.mb.repository;

import com.example.mb.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
    List<Beneficiary> findByCustomerId(long customerId);
    Beneficiary findByAccountNumber(String accountNumber);
	int countByCustomerId(long customerId);
		
}
