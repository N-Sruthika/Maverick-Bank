package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.example.mb.model.Beneficiary;
import com.example.mb.model.Branch;  // Ensure that Branch is imported
import com.example.mb.model.Customer;
import com.example.mb.repository.BeneficiaryRepository;

@ExtendWith(MockitoExtension.class)
public class BeneficiaryServiceTest {

    @InjectMocks
    private BeneficiaryService beneficiaryService;

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    private Customer c1, c2;
    private Beneficiary b1, b2, b3;
    private Branch branch;  // Add branch as a field
    
    @BeforeEach
    public void init() {
        // Initialize Branch instance
        branch = new Branch("Main Branch", "123 Main St");  // Create branch object
        branch.setId(1L);  // Set branch ID for testing
        
        // Initialize Customer instances
        c1 = new Customer();
        c2 = new Customer();

        // Initialize Beneficiary instances
        b1 = new Beneficiary(1L, "John Doe", "1234567890", "IFSC001", "Bank A", c1);
        b2 = new Beneficiary(2L, "Jane Smith", "0987654321", "IFSC002", "Bank B", c1);
        b3 = new Beneficiary(3L, "Mary Johnson", "1122334455", "IFSC003", "Bank C", c2);
    }

    @Test
    public void addBeneficiaryTest() {
        when(beneficiaryRepository.save(b1)).thenReturn(b1);
        assertEquals(b1, beneficiaryService.addBeneficiary(b1));
        verify(beneficiaryRepository, times(1)).save(b1);
    }

    @Test
    public void getBeneficiaryByIdTest_validId() throws InvalidIdException {
        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(b1));
        assertEquals(b1, beneficiaryService.getBeneficiaryById(1L));
        verify(beneficiaryRepository, times(1)).findById(1L);
    }

    @Test
    public void getBeneficiariesByCustomerIdTest_validId() throws InvalidIdException {
        List<Beneficiary> beneficiaries = Arrays.asList(b1, b2);
        when(beneficiaryRepository.findByCustomerId(1L)).thenReturn(beneficiaries);
        assertEquals(beneficiaries, beneficiaryService.getBeneficiariesByCustomerId(1L));
        verify(beneficiaryRepository, times(1)).findByCustomerId(1L);
    }
    @Test
    public void updateBeneficiaryTest() {
        when(beneficiaryRepository.save(b3)).thenReturn(b3);
        assertEquals(b3, beneficiaryService.updateBeneficiary(b3));
        verify(beneficiaryRepository, times(1)).save(b3);
    }

    @Test
    public void deleteBeneficiaryTest() {
        String result = beneficiaryService.deleteBeneficiaryById(1L);
        assertEquals("Deleted successfully", result);
        verify(beneficiaryRepository, times(1)).deleteById(1L);
    }
}
