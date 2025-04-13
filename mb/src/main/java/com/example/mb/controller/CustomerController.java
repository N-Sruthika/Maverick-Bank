package com.example.mb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.model.Customer;
import com.example.mb.service.CustomerService;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@PostMapping("/api/customer/add")
	public Customer addDetails(@RequestBody Customer customer) {
		return customerService.add(customer);
		
	}

}
