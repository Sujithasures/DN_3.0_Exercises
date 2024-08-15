package com.example.employeemanagementsystem.repository;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.employeemanagementsystem.model.EmployeeProjection;
import com.example.employeemanagementsystem.model.EmployeeDTO;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query method example
    List<Employee> findByDepartmentName(String departmentName);

    // JPQL query example
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findEmployeesByDepartmentName(@Param("departmentName") String departmentName);

    // Native SQL query example
    @Query(value = "SELECT * FROM employees e WHERE e.email LIKE %:keyword%", nativeQuery = true)
    List<Employee> findEmployeesByEmailKeyword(@Param("keyword") String keyword);
    
 // Using Named Query to find employees by department name
    List<Employee> findByDepartmentNameNamed(@Param("departmentName") String departmentName);

    // Using Named Query to find employees by email keyword
    List<Employee> findByEmailKeywordNamed(@Param("keyword") String keyword);
    
    // Find all employees with pagination
    Page<Employee> findAll(Pageable pageable);

    // Find employees by department name with pagination
    Page<Employee> findByDepartmentName(String departmentName, Pageable pageable);

 // Find all employees with sorting
    List<Employee> findAll(Sort sort);
    @Query("SELECT e.id AS id, e.name AS name, e.email AS email, d.name AS departmentName " +
            "FROM Employee e JOIN e.department d")
     List<EmployeeProjection> findAllEmployeeProjections();

    @Query("SELECT new com.example.employeemanagementsystem.model.EmployeeDTO(e.id, e.name, d.name) " +
           "FROM Employee e JOIN e.department d WHERE e.department.name = :departmentName")
    List<EmployeeDTO> findEmployeeDTOsByDepartment(@Param("departmentName") String departmentName);

}
