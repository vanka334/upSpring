package com.example.itog2.services;

import com.example.itog2.models.Employee;
import com.example.itog2.repositories.BranchRepository;
import com.example.itog2.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeservice;
    @Autowired
    private BranchRepository branchRepository;
    public List<Employee> getAllEmployeees() {
        return employeeservice.findAll();

    }
    public Employee getEmployeeById(Long id) {
        return employeeservice.findById(id).orElse(null);


    }
    public Employee saveEmployee(Employee employee, Long id) {
        var branch = branchRepository.findById(id).orElse(null);
        employee.setBranch(branch);
        return employeeservice.save(employee);
    }
    public Employee updateEmployee(Employee employee) {
        return employeeservice.save(employee);
    }
    public void deleteEmployee(Long id) {
        employeeservice.deleteById(id);
    }
}
