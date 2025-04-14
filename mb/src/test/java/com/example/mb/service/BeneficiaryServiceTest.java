package com.example.mb.service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Beneficiary;
import com.example.mb.model.Customer;
import com.example.mb.repository.BeneficiaryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BeneficiaryServiceTest {

    @Mock
    private BeneficiaryRepository beneficiaryRepository;

    @InjectMocks
    private BeneficiaryService beneficiaryService;

    private Beneficiary beneficiary;
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId(1L);

        beneficiary = new Beneficiary();
        beneficiary.setId(1L);
        beneficiary.setName("John Doe");
        beneficiary.setAccountNumber("1234567890");
        beneficiary.setBankName("Test Bank");
        beneficiary.setIfsc("TEST0001");
        beneficiary.setCustomer(customer);
    }

    @Test
    void testAddBeneficiary() {
        when(beneficiaryRepository.save(any(Beneficiary.class))).thenReturn(beneficiary);

        Beneficiary result = beneficiaryService.addBeneficiary(beneficiary);

        assertEquals("John Doe", result.getName());
        verify(beneficiaryRepository, times(1)).save(beneficiary);
    }

    @Test
    void testGetBeneficiaryById_Valid() throws InvalidIdException {
        when(beneficiaryRepository.findById(1L)).thenReturn(Optional.of(beneficiary));

        Beneficiary result = beneficiaryService.getBeneficiaryById(1L);

        assertEquals("1234567890", result.getAccountNumber());
    }

    @Test
    void testGetBeneficiaryById_Invalid() {
        when(beneficiaryRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(InvalidIdException.class, () -> beneficiaryService.getBeneficiaryById(2L));
    }

    @Test
    void testGetBeneficiariesByCustomerId_Valid() throws InvalidIdException {
        when(beneficiaryRepository.findByCustomerId(1L)).thenReturn(List.of(beneficiary));

        List<Beneficiary> result = beneficiaryService.getBeneficiariesByCustomerId(1L);

        assertEquals(1, result.size());
    }

    @Test
    void testGetBeneficiariesByCustomerId_Empty() {
        when(beneficiaryRepository.findByCustomerId(99L)).thenReturn(Collections.emptyList());

        assertThrows(InvalidIdException.class, () -> beneficiaryService.getBeneficiariesByCustomerId(99L));
    }
}
