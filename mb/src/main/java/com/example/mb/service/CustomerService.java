package com.example.mb.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Customer;
import com.example.mb.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    Logger logger = LoggerFactory.getLogger("CustomerService");

    public Customer add(Customer customer) {
    	logger.info("Added customer successfully");
        return customerRepository.save(customer);      
    }

    public Customer getById(Long id) throws InvalidIdException {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isEmpty()) 
            throw new InvalidIdException("Customer not found with ID: " + id);
        logger.info("Fetched customer with ID {}", id);
        return optional.get();
    }

	public Customer getByUsername(String username) {
		logger.info("Fetched customer with username");
		return customerRepository.findByUsername(username);
	}

	public List<Customer> getAllCustomerDetails(long bid) {
	    return customerRepository.findByBranchId(bid);
	}

}
