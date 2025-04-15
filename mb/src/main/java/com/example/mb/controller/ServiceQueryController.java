package com.example.mb.controller;

import com.example.mb.model.ServiceQuery;
import com.example.mb.service.ServiceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service-queries")
public class ServiceQueryController {

    @Autowired
    private ServiceQueryService serviceQueryService;

    @PostMapping("/add")
    public ResponseEntity<ServiceQuery> createServiceQuery(@RequestBody ServiceQuery serviceQuery) {
        ServiceQuery createdQuery = serviceQueryService.createServiceQuery(serviceQuery);
        return ResponseEntity.ok(createdQuery);
    }
}
