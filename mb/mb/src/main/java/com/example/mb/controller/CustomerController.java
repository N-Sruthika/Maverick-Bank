package com.example.mb.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        //method to add customer with corresponding branch
	    @PostMapping("/api/customer/add/{bid}")
	    public Customer addCustomer(@PathVariable long bid, @RequestBody Customer customer) throws InvalidIdException, InvalidUsernameException {
	        Branch branch = branchService.getById(bid);        
	        customer.setBranch(branch);
	        return customerService.add(customer);
	    }

	    
	   
	    
	    //get by username
//	    @GetMapping("/api/get/details/{username}")
//	    public Customer getDetailsByUsername(@PathVariable String username) {	    	
//	    	return  customerService.getByUsername(username);	
//	    	
//	    }
	    @GetMapping("/api/getall/customer/{bid}")
	    public List<Customer> getAllCustomerDetails(@PathVariable long bid) {
	        return customerService.getAllCustomerDetails(bid);
	    }
	    @GetMapping("/api/customer/get")
	    public Customer getCustomerByUserId(Principal principal) throws InvalidIdException {
	        return customerService.getCustomerByUsername(principal.getName());
	    }

	    @PostMapping("/api/newuser/signup")
	    public Customer signup(@RequestBody Customer customerSignup) throws InvalidUsernameException {
	            
	        return customerService.add(customerSignup);
	    }
	    
	    @GetMapping("/api/getall/{id}")
	    public Customer getDetails(@PathVariable Long id) throws InvalidIdException {
	        return customerService.getDetails(id);
	    }
	    @GetMapping("/api/customer/signup/ifsc/{ifscCode}")
	    public Customer getCustomerSignupByIfsc(@PathVariable String ifscCode) throws InvalidIdException {
	        return customerService.getDetailsByIfsc(ifscCode);
	    }


}

