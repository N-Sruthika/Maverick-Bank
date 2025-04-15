package com.example.mb.service;

import com.example.mb.model.ServiceQuery;
import com.example.mb.repository.ServiceQueryRepository;
import com.example.mb.service.ServiceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceQueryService {

    @Autowired
    private ServiceQueryRepository serviceQueryRepository;

    
    public ServiceQuery createServiceQuery(ServiceQuery serviceQuery) {
        return serviceQueryRepository.save(serviceQuery);
    }
}
