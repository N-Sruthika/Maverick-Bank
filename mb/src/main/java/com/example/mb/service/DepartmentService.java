package com.example.mb.service;

import com.example.mb.model.Department;
import com.example.mb.model.Department.Role;
import com.example.mb.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Add a new department
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get department by ID
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    // Get department by role
    public List<Department> getDepartmentsByRole(Role role) {
        return departmentRepository.findByRole(role);
    }

    // Update an existing department
    public Department updateDepartment(Long id, Department departmentDetails) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            department.setRole(departmentDetails.getRole());
            return departmentRepository.save(department);
        }
        return null; // Or throw an exception if needed
    }

    // Delete a department by ID
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
