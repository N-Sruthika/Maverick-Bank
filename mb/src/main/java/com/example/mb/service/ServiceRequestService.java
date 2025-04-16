package com.example.mb.service;

import com.example.mb.model.ServiceRequest;
import com.example.mb.repository.ServiceRequestRepository;
import com.example.mb.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceRequestService {

    @Autowired
    private ServiceRequestRepository repository;

    
    public List<ServiceRequest> getAllRequests() {
        return repository.findAll();
    }

    
    public List<ServiceRequest> getRequestsByStatus(String status) {
        return repository.findByStatus(status);
    }

    
    public List<ServiceRequest> getRequestsByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    
    public ServiceRequest getRequestById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
