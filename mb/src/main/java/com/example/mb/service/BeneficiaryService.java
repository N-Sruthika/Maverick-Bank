package com.example.mb.service;

import java.util.List;
import java.util.Optional;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Beneficiary;
import com.example.mb.repository.BeneficiaryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    Logger logger = LoggerFactory.getLogger("BeneficiaryService");

    // Add a new beneficiary
    public Beneficiary addBeneficiary(Beneficiary beneficiary) {      
        logger.info("Added beneficiary successfully");
        return  beneficiaryRepository.save(beneficiary);
    }

    // Get a beneficiary by ID
    public Beneficiary getBeneficiaryById(long beneficiaryId) throws InvalidIdException {
        Optional<Beneficiary> beneficiary = beneficiaryRepository.findById(beneficiaryId);
        if (beneficiary.isEmpty()) 
            throw new InvalidIdException("Beneficiary not found with your ID");
        
        logger.info("Fetched beneficiary with ID {}", beneficiaryId);
        return beneficiary.get();
    }

    // Get all beneficiaries for a customer by customerId
    public List<Beneficiary> getBeneficiariesByCustomerId(long customerId) throws InvalidIdException {
        List<Beneficiary> beneficiaries = beneficiaryRepository.findByCustomerId(customerId);
        if (beneficiaries.isEmpty()) 
            throw new InvalidIdException("No beneficiaries found for the customer ID");
        
        logger.info("Fetched {} beneficiaries for customer ID {}", beneficiaries.size(), customerId);
        return beneficiaries;
    }

    // Update an existing beneficiary
    public Beneficiary updateBeneficiary(Beneficiary beneficiary) {
        Beneficiary updated = beneficiaryRepository.save(beneficiary);
        logger.info("Updated beneficiary with ID {}", updated.getId());
        return updated;
    }

    // Delete a beneficiary by ID

	public String deleteBeneficiaryById(long beneficiaryId) {
		 logger.info("Deleted beneficiary");
		 beneficiaryRepository.deleteById(beneficiaryId);
		return "Deleted successfully";
		 
	}
}
