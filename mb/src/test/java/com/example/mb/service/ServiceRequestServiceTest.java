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
import com.example.mb.model.Customer;
import com.example.mb.model.ServiceRequest;
import com.example.mb.repository.CustomerRepository;
import com.example.mb.repository.ServiceRequestRepository;

@ExtendWith(MockitoExtension.class)
public class ServiceRequestServiceTest {

    @InjectMocks
    private ServiceRequestService serviceRequestService;

    @Mock
    private ServiceRequestRepository serviceRequestRepo;

    @Mock
    private CustomerRepository customerRepo;

    private Customer c1;
    private ServiceRequest r1, r2;

    @BeforeEach
    public void init() {
        // Initialize Customer instance
        c1 = new Customer();
        c1.setId(1L);
        c1.setName("John Doe");
        c1.setEmail("john.doe@example.com");
        c1.setAadhaarNumber("1234567890");
        c1.setAddress("123 Main St");

        // Initialize ServiceRequest instances
        r1 = new ServiceRequest(ServiceRequest.Category.ACCOUNT, "Account Issue", "Issue with account balance", 
                                "https://example.com/attachment.jpg", "Pending", c1, LocalDate.now());
        r2 = new ServiceRequest(ServiceRequest.Category.TECHNICAL, "Technical Issue", "Website not loading", 
                                "https://example.com/tech-attachment.jpg", "Pending", c1, LocalDate.now());
    }

    @Test
    public void raiseServiceRequestTest() throws InvalidIdException {
        when(customerRepo.findById(1L)).thenReturn(Optional.of(c1));
        when(serviceRequestRepo.save(r1)).thenReturn(r1);
        
        assertEquals(r1, serviceRequestService.raiseServiceRequest(1L, r1));
        verify(serviceRequestRepo, times(1)).save(r1);
    }

    
    @Test
    public void getRequestsByCustomerIdTest() throws InvalidIdException {
        List<ServiceRequest> requests = Arrays.asList(r1, r2);
        when(serviceRequestRepo.findByCustomerId(1L)).thenReturn(requests);
        
        assertEquals(requests, serviceRequestService.getRequestsByCustomerId(1L));
        verify(serviceRequestRepo, times(1)).findByCustomerId(1L);
    }

   

    @Test
    public void getRequestByIdTest() throws InvalidIdException {
        when(serviceRequestRepo.findById(1L)).thenReturn(Optional.of(r1));

        assertEquals(r1, serviceRequestService.getRequestById(1L));
        verify(serviceRequestRepo, times(1)).findById(1L);
    }

   
    @Test
    public void deleteRequestTest() throws InvalidIdException {
        when(serviceRequestRepo.findById(1L)).thenReturn(Optional.of(r1));

        String result = serviceRequestService.deleteRequest(1L);

        assertEquals("Request deleted!!", result);
        verify(serviceRequestRepo, times(1)).delete(r1);
    }

   
}
