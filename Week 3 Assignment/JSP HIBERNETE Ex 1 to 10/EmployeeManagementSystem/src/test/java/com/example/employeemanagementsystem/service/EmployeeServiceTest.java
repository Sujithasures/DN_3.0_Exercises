package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.model.Employee;
import com.example.employeemanagementsystem.primaryrepository.PrimaryEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Mock
    private PrimaryEmployeeRepository primaryEmployeeRepository;

    public EmployeeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void batchUpdateEmployees() {
        List<Employee> employees = new ArrayList<>();
        // Create and add Employee objects to the list
        for (int i = 0; i < 50; i++) {
            Employee employee = new Employee();
            employee.setId((long) i);
            employee.setName("Employee " + i);
            employees.add(employee);
        }

        // Call the batchUpdateEmployees method
        employeeService.batchUpdateEmployees(employees);

        // Verify interactions with the repository
        verify(primaryEmployeeRepository, times(1)).saveAll(employees);
    }
}
