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
        Customer customer = customerService.getById(customerId); 
        beneficiary.setCustomer(customer); 
        return beneficiaryService.addBeneficiary(beneficiary);
    }


    // GET method to fetch a beneficiary by ID
    @GetMapping("/api/beneficiary/{beneficiaryId}")
    public Beneficiary getBeneficiaryById(@PathVariable long beneficiaryId) throws InvalidIdException {
    	 return beneficiaryService.getBeneficiaryById(beneficiaryId);
       
    }

    // GET method to fetch all beneficiaries for a customer by customerId
    @GetMapping("/api/beneficiaries/customer/{customerId}")
    public List<Beneficiary> getBeneficiariesByCustomerId(@PathVariable long customerId) throws InvalidIdException {
    	return beneficiaryService.getBeneficiariesByCustomerId(customerId);
        
    }

    // PUT method to update a beneficiary by beneficiaryId
    @PutMapping("/api/beneficiary/update/{beneficiaryId}")
    public Beneficiary updateBeneficiary(@PathVariable long beneficiaryId, 
                                         @RequestBody Beneficiary updatedBeneficiary) throws InvalidIdException {
        Beneficiary existingBeneficiary = beneficiaryService.getBeneficiaryById(beneficiaryId);
        // Update beneficiary details
        existingBeneficiary.setAccountNumber(updatedBeneficiary.getAccountNumber());
        existingBeneficiary.setBankName(updatedBeneficiary.getBankName());
        existingBeneficiary.setIfsc(updatedBeneficiary.getIfsc());
        existingBeneficiary.setName(updatedBeneficiary.getName());
        return beneficiaryService.updateBeneficiary(existingBeneficiary);
    }

    // DELETE method to deactivate a beneficiary by beneficiaryId
    @DeleteMapping("/api/beneficiary/delete/{beneficiaryId}")
    public String deleteBeneficiaryById(@PathVariable long beneficiaryId) throws InvalidIdException {
        return beneficiaryService.deleteBeneficiaryById(beneficiaryId);  // Pass only ID to service
    }


}
