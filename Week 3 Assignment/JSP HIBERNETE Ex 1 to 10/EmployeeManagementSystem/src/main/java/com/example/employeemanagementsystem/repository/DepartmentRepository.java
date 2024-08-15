package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    // Method to find departments with more than a specific number of employees
    List<Department> findByEmployeesCountGreaterThan(int count);
}
