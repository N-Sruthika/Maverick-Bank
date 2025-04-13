package com.example.mb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
