package com.example.mb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Beneficiary;
import com.example.mb.repository.BeneficiaryRepository;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    // Add a new beneficiary
    public Beneficiary addBeneficiary(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    // Get a beneficiary by ID
    public Beneficiary getBeneficiaryById(long beneficiaryId) throws InvalidIdException {
        Optional<Beneficiary> beneficiary = beneficiaryRepository.findById(beneficiaryId);
        if (!beneficiary.isPresent()) {
            throw new InvalidIdException("Beneficiary not found with ID: " + beneficiaryId);
        }
        return beneficiary.get();
    }

    // Get all beneficiaries for a customer by customerId
    public List<Beneficiary> getBeneficiariesByCustomerId(long customerId) throws InvalidIdException {
        List<Beneficiary> beneficiaries = beneficiaryRepository.findByCustomerId(customerId);
        if (beneficiaries.isEmpty()) {
            throw new InvalidIdException("No beneficiaries found for customer ID: " + customerId);
        }
        return beneficiaries;
    }

    // Update an existing beneficiary
    public Beneficiary updateBeneficiary(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary); // Saving updates the existing beneficiary
    }
    public void deleteBeneficiary(long beneficiaryId) {
        beneficiaryRepository.deleteById(beneficiaryId); // Deletes the beneficiary by ID
    }

}
