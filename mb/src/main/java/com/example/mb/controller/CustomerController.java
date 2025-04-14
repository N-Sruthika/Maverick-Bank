package com.example.mb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Branch;
import com.example.mb.model.Customer;
import com.example.mb.service.BranchService;
import com.example.mb.service.CustomerService;

	@RestController
	public class CustomerController {

	    @Autowired
	    private CustomerService customerService;
	    @Autowired
	    private BranchService branchService;

	    @PostMapping("/api/customer/add/{bid}")
	    public Customer addDetails(@PathVariable long bid, @RequestBody Customer customer) throws InvalidIdException {
	        // Fetch the branch using the branchId (bid)
	        Branch branch = branchService.getById(bid);

	        // Set the branch for the customer
	        customer.setBranch(branch);

	        // If dob is not provided, set the current date
	        if (customer.getDob() == null) {
	            throw new InvalidIdException("Dod not found"); // or throw error if dob is required
	        }

	        // Save the customer using the service
	        Customer savedCustomer = customerService.add(customer);

	        // Return the saved customer
	        return savedCustomer;
	    }
	    @GetMapping("/api/get/{id}")
	    public Customer getCustomerById(@PathVariable Long id) throws InvalidIdException {
	        return customerService.getById(id);
	    }
	}

