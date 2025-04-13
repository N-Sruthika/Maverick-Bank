package com.example.mb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Beneficiary;
import com.example.mb.model.Customer;
import com.example.mb.service.BeneficiaryService;
import com.example.mb.service.CustomerService;

@RestController
public class BeneficiaryController {

    @Autowired
    private BeneficiaryService beneficiaryService;
    @Autowired
    private CustomerService customerService;

    // POST method to add a beneficiary for a customer
    @PostMapping("/api/beneficiary/add/{customerId}")
    public Beneficiary addBeneficiary(@PathVariable long customerId, 
                                       @RequestBody Beneficiary beneficiary) throws InvalidIdException {
        // Fetch the customer using customerId
        Customer customer = customerService.getById(customerId); // Assuming you have a service to fetch the customer
        if (customer == null) {
            throw new InvalidIdException("Invalid customerId: " + customerId); // Handle invalid customerId
        }

        // Set the customer for the beneficiary
        beneficiary.setCustomer(customer); // Set the actual Customer object, not just the customerId

        // Save the beneficiary using the service
        return beneficiaryService.addBeneficiary(beneficiary);
    }


    // GET method to fetch a beneficiary by ID
    @GetMapping("/api/beneficiary/{beneficiaryId}")
    public Beneficiary getBeneficiaryById(@PathVariable long beneficiaryId) throws InvalidIdException {
        // Fetch the beneficiary by ID
        Beneficiary beneficiary = beneficiaryService.getBeneficiaryById(beneficiaryId);
        if (beneficiary == null) {
            throw new InvalidIdException("Beneficiary not found with ID: " + beneficiaryId);
        }
        return beneficiary;
    }

    // GET method to fetch all beneficiaries for a customer by customerId
    @GetMapping("/api/beneficiaries/customer/{customerId}")
    public List<Beneficiary> getBeneficiariesByCustomerId(@PathVariable long customerId) throws InvalidIdException {
        // Fetch all beneficiaries for a customer by customerId
        List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiariesByCustomerId(customerId);
        if (beneficiaries.isEmpty()) {
            throw new InvalidIdException("No beneficiaries found for customer ID: " + customerId);
        }
        return beneficiaries;
    }

    // PUT method to update a beneficiary by beneficiaryId
    @PutMapping("/api/beneficiary/update/{beneficiaryId}")
    public Beneficiary updateBeneficiary(@PathVariable long beneficiaryId, 
                                         @RequestBody Beneficiary updatedBeneficiary) throws InvalidIdException {
        // Fetch the existing beneficiary by ID
        Beneficiary existingBeneficiary = beneficiaryService.getBeneficiaryById(beneficiaryId);
        if (existingBeneficiary == null) {
            throw new InvalidIdException("Beneficiary not found with ID: " + beneficiaryId);
        }

        // Update beneficiary details
        existingBeneficiary.setAccountNumber(updatedBeneficiary.getAccountNumber());
        existingBeneficiary.setBankName(updatedBeneficiary.getBankName());
        existingBeneficiary.setIfsc(updatedBeneficiary.getIfsc());
        existingBeneficiary.setName(updatedBeneficiary.getName());

        // Save updated beneficiary and return it
        return beneficiaryService.updateBeneficiary(existingBeneficiary);
    }

    // DELETE method to deactivate a beneficiary by beneficiaryId
    @DeleteMapping("/api/beneficiary/delete/{beneficiaryId}")
    public String deleteBeneficiary(@PathVariable long beneficiaryId) throws InvalidIdException {
        // Fetch the beneficiary using beneficiaryId
        Beneficiary beneficiary = beneficiaryService.getBeneficiaryById(beneficiaryId);
        if (beneficiary == null) {
            throw new InvalidIdException("Invalid beneficiaryId: " + beneficiaryId); // Handle invalid beneficiaryId
        }

        // Delete the beneficiary using the service
        beneficiaryService.deleteBeneficiary(beneficiaryId);

        return "Beneficiary deleted successfully.";
    }

}
