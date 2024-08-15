package com.example.employeemanagementsystem;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Using named queries
        List<Employee> hrEmployees = employeeRepository.findByDepartmentNameNamed("Human Resources");
        hrEmployees.forEach(emp -> System.out.println("HR Employee: " + emp.getName()));

        List<Employee> employeesByEmail = employeeRepository.findByEmailKeywordNamed("john");
        employeesByEmail.forEach(emp -> System.out.println("Employee with 'john' in email: " + emp.getName()));
    }
}
