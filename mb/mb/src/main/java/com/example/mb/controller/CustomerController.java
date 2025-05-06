package com.example.mb.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.exception.InvalidUsernameException;
import com.example.mb.model.Branch;
import com.example.mb.model.Customer;
import com.example.mb.service.BranchService;
import com.example.mb.service.CustomerService;

@RestController
@CrossOrigin(origins = {"http://localhost:5173"})
public class CustomerController {

	    @Autowired
	    private CustomerService customerService;
	    @Autowired
	    private BranchService branchService;
	    
       	@GetMapping("/api/customer/get")
	    public Customer getCustomerByUserId(Principal principal) throws InvalidIdException {
	        return customerService.getCustomerByUsername(principal.getName());
	    }
	    @PutMapping("/api/customer/updateProfile/{id}")
	    public Customer updateProfile(@PathVariable Long id,@RequestBody Customer customer) throws InvalidIdException {
	    	Customer existingCustomer = customerService.getDetails(id);
	    	if (customer.getEmail() != null) {
	            existingCustomer.setEmail(customer.getEmail());
	        }
	        if (customer.getAddress() != null) {
	            existingCustomer.setAddress(customer.getAddress());
	        }
	    	return customerService.updateProfile(existingCustomer);
	    }

	    @PostMapping("/api/newuser/signup")
	    public Customer signup(@RequestBody Customer customerSignup) throws InvalidUsernameException {
	            
	        return customerService.add(customerSignup);
	    }
	    
	    @GetMapping("/api/getall/{id}")
	    public Customer getDetails(@PathVariable Long id) throws InvalidIdException {
	        return customerService.getDetails(id);
	    }
	   

}

