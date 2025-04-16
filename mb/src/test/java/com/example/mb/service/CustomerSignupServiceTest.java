package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.CustomerSignup;
import com.example.mb.repository.CustomerSignupRepository;

public class CustomerSignupServiceTest {

    @Mock
    private CustomerSignupRepository customerSignupRepository;

    @InjectMocks
    private CustomerSignupService customerSignupService;

    private CustomerSignup sampleCustomer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initializes mocks

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
    public void testGetDetailsByIfsc_Valid() throws InvalidIdException {
        when(customerSignupRepository.findByIfscCode("ABC123456")).thenReturn(sampleCustomer);

        CustomerSignup result = customerSignupService.getDetailsByIfsc("ABC123456");

        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }

    @Test
    public void testGetDetailsByIfsc_Invalid() {
        when(customerSignupRepository.findByIfscCode("XYZ000")).thenReturn(null);

        assertThrows(InvalidIdException.class, () -> customerSignupService.getDetailsByIfsc("XYZ000"));
    }
}
