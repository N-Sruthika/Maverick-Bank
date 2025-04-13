package com.example.mb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Customer;
import com.example.mb.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	public Customer add(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	public Customer getById(Long id) throws InvalidIdException {
		Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new InvalidIdException("Customer not found with ID: " + id);
        }
	}

}
