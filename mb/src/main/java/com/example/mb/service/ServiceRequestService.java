package com.example.mb.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger("ServiceRequestService");

    public ServiceRequest raiseServiceRequest(Long customerId, ServiceRequest request) throws InvalidIdException {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if(customer.isEmpty())
        	throw new InvalidIdException("Invalid Id!!");
        request.setCustomer(customer.get());
        request.setCreatedDate(LocalDate.now());
        request.setStatus("Pending");
        logger.info(" Your query is raised ");
        return  serviceRequestRepo.save(request);
    }

    public List<ServiceRequest> getRequestsByCustomerId(Long customerId) throws InvalidIdException {
        List<ServiceRequest> list = serviceRequestRepo.findByCustomerId(customerId);
        if (list.isEmpty())
            throw new InvalidIdException("No service requests found for customer ID: " + customerId);
        logger.info("Fetched {} service requests for customer ID {}", list.size(), customerId);
        return list;
    }

    public ServiceRequest getRequestById(Long requestId) throws InvalidIdException {
        Optional<ServiceRequest> optionalRequest = serviceRequestRepo.findById(requestId);
        if (optionalRequest.isEmpty()) 
            throw new InvalidIdException("Service Request not found with ID: " + requestId);        
        logger.info("Fetched service request with ID {}", requestId);
        return optionalRequest.get();
    }

    public String deleteRequest(Long requestId) throws InvalidIdException {
        ServiceRequest req = getRequestById(requestId);
        serviceRequestRepo.delete(req);
        logger.info("Service request deleted successfully");
		return "Request deleted!!";
    }
}
