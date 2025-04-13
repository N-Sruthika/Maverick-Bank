package com.example.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mb.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
