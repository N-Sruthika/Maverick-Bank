package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.CustomerSignup;
import com.example.mb.repository.CustomerSignupRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerSignupServiceTest {

    @Mock
    private CustomerSignupRepository customerSignupRepository;

    @InjectMocks
    private CustomerSignupService customerSignupService;

    private CustomerSignup sampleCustomer;

    @BeforeEach
    public void setup() {
        sampleCustomer = new CustomerSignup();
        sampleCustomer.setId(1L);
        sampleCustomer.setName("Alice");
        sampleCustomer.setIfscCode("ABC123456");
    }

    @Test
    public void testAdd() {
        when(customerSignupRepository.save(sampleCustomer)).thenReturn(sampleCustomer);

        CustomerSignup result = customerSignupService.add(sampleCustomer);
        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }

    @Test
    public void testGetDetailsByIfscValid() throws InvalidIdException {
        // Mocking Optional return
        when(customerSignupRepository.findByIfscCode("ABC123456"))
            .thenReturn(Optional.of(sampleCustomer));

        CustomerSignup result = customerSignupService.getDetailsByIfsc("ABC123456");
        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }

    @Test
    public void testGetDetailsByIdValid() throws InvalidIdException {
        // THIS is the correct way to mock findById which returns Optional
        when(customerSignupRepository.findById(1L))
            .thenReturn(Optional.of(sampleCustomer));

        CustomerSignup result = customerSignupService.getDetails(1L);
        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }
}
