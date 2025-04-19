package com.example.mb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.mb.exception.InvalidIdException;
import com.example.mb.model.ServiceRequest;
import com.example.mb.service.ServiceRequestService;

@RestController
@RequestMapping("/api/service-request")
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

    // GET: Get request by ID
    @GetMapping("/{requestId}")
    public ServiceRequest getRequestById(@PathVariable Long requestId) throws InvalidIdException {
        return serviceRequestService.getRequestById(requestId);
    }

    // DELETE: Delete request by ID
    @DeleteMapping("/delete/{requestId}")
    public String deleteRequest(@PathVariable Long requestId) throws InvalidIdException {
    	return serviceRequestService.deleteRequest(requestId);
       
    }
}
