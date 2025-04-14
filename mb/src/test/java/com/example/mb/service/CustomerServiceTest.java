package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Customer;
import com.example.mb.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer sampleCustomer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleCustomer = new Customer();
        sampleCustomer.setId(1L);
        sampleCustomer.setName("John Doe");
        sampleCustomer.setEmail("john@example.com");
    }

    @Test
    public void testAddCustomer() {
        when(customerRepository.save(sampleCustomer)).thenReturn(sampleCustomer);

        Customer result = customerService.add(sampleCustomer);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerRepository, times(1)).save(sampleCustomer);
    }

    @Test
    public void testGetCustomerById_Success() throws InvalidIdException {
        when(customerRepository.findById(1L)).thenReturn(sampleCustomer);

        Customer result = customerService.getById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetCustomerById_NotFound() {
        when(customerRepository.findById(2L)).thenReturn(null);

        assertThrows(InvalidIdException.class, () -> {
            customerService.getById(2L);
        });

        verify(customerRepository, times(1)).findById(2L);
    }
}
