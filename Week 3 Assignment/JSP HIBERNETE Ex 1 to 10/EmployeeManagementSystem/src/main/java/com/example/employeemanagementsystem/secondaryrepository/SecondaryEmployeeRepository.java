package com.example.employeemanagementsystem.secondaryrepository;

import com.example.employeemanagementsystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SecondaryEmployeeRepository extends JpaRepository<Employee, Long> {

    // Custom query to find employees by department name
    @Query("SELECT e FROM Employee e WHERE e.department.name = :departmentName")
    List<Employee> findByDepartmentNameCustom(@Param("departmentName") String departmentName);

    // Custom query to find employees with a name containing a specific keyword
    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:keyword%")
    List<Employee> findByNameKeyword(@Param("keyword") String keyword);

    // Custom query to find employees by email domain (e.g., @gmail.com)
    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:domain")
    List<Employee> findByEmailDomain(@Param("domain") String domain);

    // Custom query to find employees with a department ID greater than a specified value
    @Query("SELECT e FROM Employee e WHERE e.department.id > :departmentId")
    List<Employee> findByDepartmentIdGreaterThan(@Param("departmentId") Long departmentId);
}
