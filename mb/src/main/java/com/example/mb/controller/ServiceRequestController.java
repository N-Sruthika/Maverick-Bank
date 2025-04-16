package com.example.mb.controller;

import com.example.mb.model.ServiceRequest;
import com.example.mb.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService service;

    @GetMapping("/getAll")
    public List<ServiceRequest> getAllRequests() {
        return service.getAllRequests();
    }

    @GetMapping("/status/{status}")
    public List<ServiceRequest> getRequestsByStatus(@PathVariable String status) {
        return service.getRequestsByStatus(status);
    }

    @GetMapping("/customer/{customerId}")
    public List<ServiceRequest> getRequestsByCustomerId(@PathVariable Long customerId) {
        return service.getRequestsByCustomerId(customerId);
    }

    @GetMapping("/{id}")
    public ServiceRequest getRequestById(@PathVariable Long id) {
        return service.getRequestById(id);
    }
}
