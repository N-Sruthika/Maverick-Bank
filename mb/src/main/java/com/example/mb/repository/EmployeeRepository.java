package com.example.mb.repository;

import com.example.mb.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name); 
    List<Employee> findByDepartmentId(Long departmentId);        
}
