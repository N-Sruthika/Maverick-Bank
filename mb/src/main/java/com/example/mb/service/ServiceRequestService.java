package com.example.mb.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.Customer;
import com.example.mb.model.ServiceRequest;
import com.example.mb.repository.CustomerRepository;
import com.example.mb.repository.ServiceRequestRepository;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepo;

    @Autowired
    private CustomerRepository customerRepo;

    public ServiceRequest raiseServiceRequest(Long customerId, ServiceRequest request) throws InvalidIdException {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            throw new InvalidIdException("Invalid customer ID: " + customerId);
        }

        Customer customer = optionalCustomer.get();
        request.setCustomer(customer);
        request.setCreatedDate(LocalDate.now());
        request.setStatus("Pending");

        return serviceRequestRepo.save(request);
    }

    public List<ServiceRequest> getRequestsByCustomerId(Long customerId) throws InvalidIdException {
        List<ServiceRequest> list = serviceRequestRepo.findByCustomerId(customerId);
        if (list.isEmpty()) {
            throw new InvalidIdException("No service requests found for customer ID: " + customerId);
        }
        return list;
    }

    public ServiceRequest getRequestById(Long requestId) throws InvalidIdException {
        Optional<ServiceRequest> optionalRequest = serviceRequestRepo.findById(requestId);
        if (!optionalRequest.isPresent()) {
            throw new InvalidIdException("Service Request not found with ID: " + requestId);
        }
        return optionalRequest.get();
    }

    public void deleteRequest(Long requestId) throws InvalidIdException {
        ServiceRequest req = getRequestById(requestId);
        serviceRequestRepo.delete(req);
    }
}
