package com.example.mb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mb.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Optional<Customer> findById(Long id);

	

	List<Customer> findByBranchId(long bid);

	Customer findByUserId(long userId);

	Optional<Customer> findByIfscCode(String ifscCode);



	Customer findByUserUsername(String name);



	

}
