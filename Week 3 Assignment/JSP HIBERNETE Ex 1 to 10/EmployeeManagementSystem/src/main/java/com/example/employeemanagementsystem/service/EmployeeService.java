package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.primaryrepository.PrimaryEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private PrimaryEmployeeRepository primaryEmployeeRepository;

    public List<Employee> getEmployeesByName(String name) {
        return primaryEmployeeRepository.findByNameCustom(name);
    }

    public List<Employee> getEmployeesByEmailKeyword(String keyword) {
        return primaryEmployeeRepository.findByEmailKeyword(keyword);
    }

    public List<Employee> getEmployeesByDepartmentName(String departmentName) {
        return primaryEmployeeRepository.findByDepartmentNameCustom(departmentName);
    }

    public List<Employee> getEmployeesByDepartmentIdGreaterThan(Long departmentId) {
        return primaryEmployeeRepository.findByDepartmentIdGreaterThan(departmentId);
    }
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void batchUpdateEmployees(List<Employee> employees) {
        int batchSize = 20; // Match this with hibernate.jdbc.batch_size property
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            entityManager.merge(employee);

            if (i % batchSize == 0 && i > 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}

