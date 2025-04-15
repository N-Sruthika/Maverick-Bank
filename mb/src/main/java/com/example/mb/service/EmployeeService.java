package com.example.mb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mb.model.Employee;
import com.example.mb.repository.EmployeeRepository;
import com.example.mb.service.EmployeeService;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id).map(employee -> {
            employee.setName(updatedEmployee.getName());
            employee.setDob(updatedEmployee.getDob());
            employee.setGender(updatedEmployee.getGender());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setMobileNo(updatedEmployee.getMobileNo());
            employee.setAddress(updatedEmployee.getAddress());
            employee.setProfilePicUrl(updatedEmployee.getProfilePicUrl());
            employee.setGovernmentIdProofUrl(updatedEmployee.getGovernmentIdProofUrl());
            employee.setAddressProofUrl(updatedEmployee.getAddressProofUrl());
            employee.setBranch(updatedEmployee.getBranch());
            employee.setDepartment(updatedEmployee.getDepartment());
            return employeeRepository.save(employee);
        }).orElse(null);
    }

    
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    
    public List<Employee> getEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}

