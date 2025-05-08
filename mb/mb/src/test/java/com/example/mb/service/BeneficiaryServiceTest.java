package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Beneficiary;
import com.example.mb.model.Customer;
import com.example.mb.repository.BeneficiaryRepository;

@ExtendWith(MockitoExtension.class)
public class BeneficiaryServiceTest {

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @InjectMocks
    private BeneficiaryService beneficiaryService;

    private Beneficiary beneficiary;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        // Create a mock Customer object
    	customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setAddress("New York");

        // Create a mock Beneficiary object using the constructor
        beneficiary = new Beneficiary(1L, "Jane Smith", "9876543210", "IFSC1234", "Sample Bank", customer);
    }

    @Test
    public void addBeneficiaryTest() {
        when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);

        // Test adding a new beneficiary
        Beneficiary addedBeneficiary = beneficiaryService.addBeneficiary(beneficiary);

        assertEquals(beneficiary, addedBeneficiary);
        verify(beneficiaryRepository, times(1)).save(beneficiary);
    }

    @Test
    public void testGetBeneficiaryById() throws InvalidIdException {
        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));

        // Test: get beneficiary by ID
        Beneficiary foundBeneficiary = beneficiaryService.getBeneficiaryById(1L);
        assertEquals("Jane Smith", foundBeneficiary.getName());
        verify(beneficiaryRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetBeneficiariesByCustomerId() throws InvalidIdException {
        List<Beneficiary> beneficiaries = new ArrayList<>();
        beneficiaries.add(beneficiary);

        when(beneficiaryRepository.findByCustomerId(1L)).thenReturn(beneficiaries);

        // Test: get beneficiaries by customer ID
        List<Beneficiary> foundBeneficiaries = beneficiaryService.getBeneficiariesByCustomerId(1L);
        assertEquals(1, foundBeneficiaries.size());
        assertEquals("Jane Smith", foundBeneficiaries.get(0).getName());
        verify(beneficiaryRepository, times(1)).findByCustomerId(1L);
    }

    @Test
    public void updateBeneficiaryTest() {
        beneficiary.setBankName("Updated Bank");
        when(beneficiaryRepository.save(beneficiary)).thenReturn(beneficiary);

        // Test updating the beneficiary
        Beneficiary updatedBeneficiary = beneficiaryService.updateBeneficiary(beneficiary);

        assertEquals("Updated Bank", updatedBeneficiary.getBankName());
        verify(beneficiaryRepository, times(1)).save(beneficiary);
    }

    @Test
    public void deleteBeneficiaryByIdTest() {
        // Test deleting beneficiary by ID
        String response = beneficiaryService.deleteBeneficiaryById(1L);

        assertEquals("Deleted successfully", response);
        verify(beneficiaryRepository, times(1)).deleteById(1L);
    }
}
