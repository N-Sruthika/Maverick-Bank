package com.example.mb.service;

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
        Customer saved = customerRepository.save(customer);
        logger.info("Added customer with ID {}", saved.getId());
        return saved;
    }

    public Customer getById(Long id) throws InvalidIdException {
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new InvalidIdException("Customer not found with ID: " + id);
        }
        logger.info("Fetched customer with ID {}", id);
        return customer;
    }
}
