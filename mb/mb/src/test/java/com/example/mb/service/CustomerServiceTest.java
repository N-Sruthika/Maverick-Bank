package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.exception.InvalidUsernameException;
import com.example.mb.model.Branch;
import com.example.mb.model.Customer;
import com.example.mb.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer c1, c2;
    private Branch branch1, branch2;

    @BeforeEach
    public void init() {
        branch1 = new Branch(1L, "Main Branch", "123 Main St");
        branch2 = new Branch(2L, "Secondary Branch", "456 Secondary St");

        c1 = new Customer();
        c2 = new Customer();
    }

    @Test
    public void addCustomerTest() throws InvalidUsernameException {
        when(customerRepository.save(c1)).thenReturn(c1);
        assertEquals(c1, customerService.add(c1));
        verify(customerRepository, times(1)).save(c1);
    }

    @Test
    public void getCustomerByIdTest() throws InvalidIdException {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(c1));
        assertEquals(c1, customerService.getById(1L));
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void getCustomerByUsernameTest() {
        when(customerRepository.findByUserUsername("customer1")).thenReturn(c1);
        assertEquals(c1, customerService.getCustomerByUsername("customer1"));
        verify(customerRepository, times(1)).findByUserUsername("customer1");
    }

    @Test
    public void getCustomersByBranchIdTest() {
        List<Customer> customers = Arrays.asList(c1, c2);
        when(customerRepository.findByBranchId(1L)).thenReturn(customers);
        assertEquals(customers, customerService.getAllCustomerDetails(1L));
        verify(customerRepository, times(1)).findByBranchId(1L);
    }

}
