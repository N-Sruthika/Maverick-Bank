package com.example.mb.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.exception.InvalidUsernameException;
import com.example.mb.model.Customer;
import com.example.mb.model.User;
import com.example.mb.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
 	private AuthService authService;
 
    Logger logger = LoggerFactory.getLogger("CustomerService");

    public Customer getById(Long id) throws InvalidIdException {
        Optional<Customer> optional = customerRepository.findById(id);
        if (optional.isEmpty()) 
            throw new InvalidIdException("Customer not found with ID: " + id);
        logger.info("Fetched customer with ID {}", id);
        return optional.get();
    }


	
	public Customer add(Customer customer) throws InvalidUsernameException {
    	User user=customer.getUser();
    	user.setRole("CUSTOMER");
    	user=authService.signUp(user);
    	customer.setUser(user);
    	logger.info("Added customer signup with ID");
    	 return  customerRepository.save(customer);
        
       
    }

    // Method to retrieve customer details by ID
    public Customer getDetails(Long id) throws InvalidIdException {
        Optional<Customer> optionalSignup = customerRepository.findById(id);
        if (optionalSignup.isEmpty()) 
            throw new InvalidIdException("Customer not found for id: " + id);
        logger.info("Fetched customer signup with ID {}", id);
        return optionalSignup.get();
    }


	public Customer getCustomerByUsername(String name) {
		// TODO Auto-generated method stub
		return customerRepository.findByUserUsername(name);
	}

	public Customer updateProfile(Customer existingCustomer) {
		
		return customerRepository.save(existingCustomer);
	}

	
}




