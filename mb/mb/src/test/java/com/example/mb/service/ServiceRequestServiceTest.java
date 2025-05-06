package com.example.mb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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
import com.example.mb.model.Customer;
import com.example.mb.model.ServiceRequest;
import com.example.mb.repository.CustomerRepository;
import com.example.mb.repository.ServiceRequestRepository;

@ExtendWith(MockitoExtension.class)
public class ServiceRequestServiceTest {

    @Mock
    private ServiceRequestRepository serviceRequestRepo;

    @Mock
    private CustomerRepository customerRepo;

    @InjectMocks
    private ServiceRequestService serviceRequestService;

    private ServiceRequest serviceRequest;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        // Create a mock Customer object
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");
        customer.setAddress("New York");

        // Create a mock ServiceRequest object using the constructor
        serviceRequest = new ServiceRequest(1L,
                ServiceRequest.Category.ACCOUNT,
                "Account Issue",
                "Having trouble accessing my account",
               
                "Pending",
                customer,
                LocalDate.now()
        );
    }

    @Test
    public void testRaiseServiceRequest() throws InvalidIdException {
        // Mock the behavior of customerRepo
        when(customerRepo.findById(1L)).thenReturn(Optional.of(customer));
        when(serviceRequestRepo.save(serviceRequest)).thenReturn(serviceRequest);

        // Test: raise service request
        ServiceRequest raisedRequest = serviceRequestService.raiseServiceRequest(1L, serviceRequest);

        assertEquals(serviceRequest, raisedRequest);
        verify(serviceRequestRepo, times(1)).save(serviceRequest);
        verify(customerRepo, times(1)).findById(1L);
    }

    @Test
    public void testGetRequestsByCustomerId() throws InvalidIdException {
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        serviceRequests.add(serviceRequest);

        // Mock the behavior of serviceRequestRepo
        when(serviceRequestRepo.findByCustomerId(1L)).thenReturn(serviceRequests);

        // Test: get service requests by customer ID
        List<ServiceRequest> fetchedRequests = serviceRequestService.getRequestsByCustomerId(1L);

        assertEquals(1, fetchedRequests.size());
        assertEquals("Account Issue", fetchedRequests.get(0).getSubject());
        verify(serviceRequestRepo, times(1)).findByCustomerId(1L);
    }

   

    @Test
    public void testGetAllCategories() {
        // Test: get all categories
        List<String> categories = serviceRequestService.getAllCategories();

        assertEquals(3, categories.size());
        assertEquals("ACCOUNT", categories.get(0));
        assertEquals("LOAN", categories.get(1));
        assertEquals("TECHNICAL", categories.get(2));
    }
}
