package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.secondaryrepository.SecondaryEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecondaryService {

    @Autowired
    private SecondaryEmployeeRepository secondaryEmployeeRepository;

    public List<Employee> getEmployeesByDepartmentName(String departmentName) {
        return secondaryEmployeeRepository.findByDepartmentNameCustom(departmentName);
    }

    public List<Employee> getEmployeesByNameKeyword(String keyword) {
        return secondaryEmployeeRepository.findByNameKeyword(keyword);
    }

    public List<Employee> getEmployeesByEmailDomain(String domain) {
        return secondaryEmployeeRepository.findByEmailDomain(domain);
    }

    public List<Employee> getEmployeesByDepartmentIdGreaterThan(Long departmentId) {
        return secondaryEmployeeRepository.findByDepartmentIdGreaterThan(departmentId);
    }
}
