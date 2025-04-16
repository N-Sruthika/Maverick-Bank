package com.example.mb.service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Customer;
import com.example.mb.model.ServiceRequest;
import com.example.mb.repository.CustomerRepository;
import com.example.mb.repository.ServiceRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceRequestServiceTest {

    @InjectMocks
    private ServiceRequestService serviceRequestService;

    @Mock
    private ServiceRequestRepository serviceRequestRepo;

    @Mock
    private CustomerRepository customerRepo;

    private Customer customer;
    private ServiceRequest request;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setId(1L);
        customer.setName("John");

        request = new ServiceRequest();
        request.setSubject("Issue with login");
        request.setMessage("Cannot login with my credentials");
        
        request.setAttachmentUrl(null);
    }

    @Test
    public void testRaiseServiceRequest_Valid() throws InvalidIdException {
        when(customerRepo.findById(1L)).thenReturn(customer);
        when(serviceRequestRepo.save(any(ServiceRequest.class))).thenAnswer(i -> i.getArgument(0));

        ServiceRequest result = serviceRequestService.raiseServiceRequest(1L, request);

        assertEquals("Issue with login", result.getSubject());
        assertEquals("Pending", result.getStatus());
        assertEquals(LocalDate.now(), result.getCreatedDate());
        assertEquals(customer, result.getCustomer());
    }

    @Test
    public void testRaiseServiceRequest_InvalidCustomer() {
        when(customerRepo.findById(99L)).thenReturn(null);

        assertThrows(InvalidIdException.class, () -> {
            serviceRequestService.raiseServiceRequest(99L, request);
        });
    }

    @Test
    public void testGetRequestsByCustomerId_Valid() throws InvalidIdException {
        when(serviceRequestRepo.findByCustomerId(1L)).thenReturn(Arrays.asList(request));

        var result = serviceRequestService.getRequestsByCustomerId(1L);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testGetRequestsByCustomerId_Empty() {
        when(serviceRequestRepo.findByCustomerId(2L)).thenReturn(Arrays.asList());

        assertThrows(InvalidIdException.class, () -> {
            serviceRequestService.getRequestsByCustomerId(2L);
        });
    }

    @Test
    public void testGetRequestById_Valid() throws InvalidIdException {
        when(serviceRequestRepo.findById(1L)).thenReturn(Optional.of(request));

        ServiceRequest result = serviceRequestService.getRequestById(1L);
        assertNotNull(result);
    }

    @Test
    public void testGetRequestById_Invalid() {
        when(serviceRequestRepo.findById(5L)).thenReturn(Optional.empty());

        assertThrows(InvalidIdException.class, () -> {
            serviceRequestService.getRequestById(5L);
        });
    }

    @Test
    public void testDeleteRequest_Valid() throws InvalidIdException {
        when(serviceRequestRepo.findById(1L)).thenReturn(Optional.of(request));

        serviceRequestService.deleteRequest(1L);
        verify(serviceRequestRepo, times(1)).delete(request);
    }
}
