package com.example.mb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mb.model.ServiceRequest;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {
    List<ServiceRequest> findByCustomerId(Long customerId);
}
