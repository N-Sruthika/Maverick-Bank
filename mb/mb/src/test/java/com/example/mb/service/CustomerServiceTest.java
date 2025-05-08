package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.exception.InvalidUsernameException;
import com.example.mb.model.Customer;
import com.example.mb.model.User;
import com.example.mb.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private AuthService authService;


    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        // Create a mock User object
        User user = new User();
        user.setId(1);
        user.setUsername("john");

        // Create a mock Customer object using the constructor
        customer = new Customer(1L, 
                "John Doe", 
                LocalDate.of(1990, 1, 1), 
                Customer.Gender.MALE, 
                "john@example.com", 
                "9999999999", 
                "123 Main St", 
                "New York", 
                "NY", 
                "10001", 
                "ABCDE1234F", 
                "123456789012", 
                "1234567890", 
                "IFSC0001", 
                user);
    }

    @Test
    public void addCustomerTest() throws InvalidUsernameException {
        // Corrected: Use the customer object here instead of save
        when(customerRepository.save(customer)).thenReturn(customer);

        // Test adding a new customer
        assertEquals(customer, customerService.add(customer));
        verify(customerRepository, times(1)).save(customer);
    }

   

    @Test
    public void testGetCustomerById() throws InvalidUsernameException, InvalidIdException {
       
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        // Test: get customer by ID
        Customer foundCustomer = customerService.getById(1L);
        assertEquals("John Doe", foundCustomer.getName());
        verify(customerRepository, times(1)).findById(1L);
    }
    @Test
    public void updateProfileTest() {
        customer.setCity("Los Angeles");
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer updatedCustomer = customerService.updateProfile(customer);

        assertEquals("Los Angeles", updatedCustomer.getCity());
        verify(customerRepository, times(1)).save(customer);
    }


    @Test
    public void getByUsernameTest() {
        // Mock the repository to return a customer when searched by username
        when(customerRepository.findByUserUsername("john")).thenReturn(customer);

        // Test: get customer by username
        assertEquals(customer, customerService.getCustomerByUsername("john"));
        verify(customerRepository, times(1)).findByUserUsername("john");
    }
}
