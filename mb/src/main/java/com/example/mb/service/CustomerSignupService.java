package com.example.mb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.CustomerSignup;
import com.example.mb.repository.CustomerSignupRepository;

@Service
public class CustomerSignupService {

    @Autowired
    private CustomerSignupRepository customerSignupRepository;

    public CustomerSignup add(CustomerSignup customerSignup) {
        // Optional: Validation checks can be added here
        return customerSignupRepository.save(customerSignup);
    }

    public CustomerSignup getDetails(Long id) throws InvalidIdException {
        Optional<CustomerSignup> customer = customerSignupRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new InvalidIdException("Customer with ID " + id + " not found.");
        }
    }

	public CustomerSignup getDetailsByIfsc(String ifscCode) throws InvalidIdException {
		 Optional<CustomerSignup> customer = customerSignupRepository.findByIfscCode(ifscCode);
	        if (customer.isPresent()) {
	            return customer.get();
	        } else {
	            throw new InvalidIdException("Customer with ID  not found.");
	        }
	}
	        
}
