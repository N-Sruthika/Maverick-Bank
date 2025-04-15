package com.example.mb.repository;

import com.example.mb.model.Department;
import com.example.mb.model.Department.Role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	List<Department> findByRole(Role role);
}
