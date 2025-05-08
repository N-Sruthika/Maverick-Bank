package com.example.mb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.ServiceRequest;
import com.example.mb.model.ServiceRequest.Category;
import com.example.mb.service.ServiceRequestService;

@RestController
@RequestMapping("/api/service-request")
@CrossOrigin(origins = {"http://localhost:5173"})
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    // POST: Raise a new request
    @PostMapping("/raise/{customerId}")
    public ServiceRequest raiseRequest(@PathVariable Long customerId, @RequestBody ServiceRequest request) throws InvalidIdException {
        return serviceRequestService.raiseServiceRequest(customerId, request);
    }

    // GET: Get all requests by customer
    @GetMapping("/customer/{customerId}")
    public List<ServiceRequest> getRequestsByCustomer(@PathVariable Long customerId) throws InvalidIdException {
        return serviceRequestService.getRequestsByCustomerId(customerId);
    }
    @GetMapping("/categories")
    public List<String> getCategories() {
        return serviceRequestService.getAllCategories();
    }
   
}
