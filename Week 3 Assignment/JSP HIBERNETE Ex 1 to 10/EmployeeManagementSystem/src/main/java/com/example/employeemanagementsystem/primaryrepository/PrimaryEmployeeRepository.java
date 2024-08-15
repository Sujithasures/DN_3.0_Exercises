package com.example.employeemanagementsystem.primaryrepository;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PrimaryEmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom query to find employees by name
    @Query("SELECT e FROM Employee e WHERE e.name = :name")
    List<Employee> findByNameCustom(@Param("name") String name);

    // Custom query to find employees by email containing a specific keyword
    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:keyword%")
    List<Employee> findByEmailKeyword(@Param("keyword") String keyword);

    // Custom query to find employees by department name
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findByDepartmentNameCustom(@Param("departmentName") String departmentName);

    // Custom query to find employees with a department ID greater than a specified value
    @Query("SELECT e FROM Employee e WHERE e.department.id > :departmentId")
    List<Employee> findByDepartmentIdGreaterThan(@Param("departmentId") Long departmentId);
}
