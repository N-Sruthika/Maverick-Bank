package com.example.mb.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.CustomerSignup;
import com.example.mb.repository.CustomerSignupRepository;

@Service
public class CustomerSignupService {

    @Autowired
    private CustomerSignupRepository customerSignupRepository;

    Logger logger = LoggerFactory.getLogger("CustomerSignupService");

    // Method to save a new customer signup
    public CustomerSignup add(CustomerSignup customerSignup) {
        CustomerSignup saved = customerSignupRepository.save(customerSignup);
        logger.info("Added customer signup with ID {}", saved.getId());
        return saved;
    }

    // Method to retrieve customer details by ID
    public CustomerSignup getDetails(Long id) throws InvalidIdException {
        Optional<CustomerSignup> optionalSignup = customerSignupRepository.findById(id);
        if (optionalSignup.isEmpty()) 
            throw new InvalidIdException("Customer not found for id: " + id);
        logger.info("Fetched customer signup with ID {}", id);
        return optionalSignup.get();
    }

    // Method to retrieve customer details by IFSC code
    public CustomerSignup getDetailsByIfsc(String ifscCode) throws InvalidIdException {
        CustomerSignup customerSignup = customerSignupRepository.findByIfscCode(ifscCode);
        if (customerSignup == null) {
            throw new InvalidIdException("Customer not found for IFSC code: " + ifscCode);
        }
        logger.info("Fetched customer signup with IFSC code {}", ifscCode);
        return customerSignup;
    }
}
