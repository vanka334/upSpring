package com.example.itog2.repositories;

import com.example.itog2.models.Branch;
import com.example.itog2.models.Employee;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByEmail(@Email String email);
}
