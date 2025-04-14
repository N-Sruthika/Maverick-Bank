package com.example.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.CustomerSignup;
import com.example.mb.repository.CustomerSignupRepository;

@Service
public class CustomerSignupService {

    @Autowired
    private CustomerSignupRepository customerSignupRepository;

    // Method to save a new customer signup
    public CustomerSignup add(CustomerSignup customerSignup) {
        return customerSignupRepository.save(customerSignup);
    }

    // Method to retrieve customer details by ID
    public CustomerSignup getDetails(Long id) throws InvalidIdException {
        return customerSignupRepository.findById(id).orElseThrow(() -> new InvalidIdException("Customer not found for id: " + id));
    }

    // Method to retrieve customer details by IFSC code
    public CustomerSignup getDetailsByIfsc(String ifscCode) throws InvalidIdException {
        CustomerSignup customerSignup = customerSignupRepository.findByIfscCode(ifscCode);
        if (customerSignup == null) {
            throw new InvalidIdException("Customer not found for IFSC code: " + ifscCode);
        }
        return customerSignup;
    }
}
