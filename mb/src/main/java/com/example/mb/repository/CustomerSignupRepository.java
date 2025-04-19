package com.example.mb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mb.model.CustomerSignup;

public interface CustomerSignupRepository extends JpaRepository<CustomerSignup, Long> {
    
   
    CustomerSignup findById(long id); // This will return null if not found

    Optional<CustomerSignup> findByIfscCode(String ifscCode);

}
