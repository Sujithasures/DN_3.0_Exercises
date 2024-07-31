class Employee {
    private String employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}
class EmployeeManagementSystem {
    private Employee[] employees;
    private int count;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        count = 0;
    }

    // Method to add an employee
    public boolean addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count++] = employee;
            return true;
        }
        return false; // Array is full
    }

    // Method to search for an employee by employeeId
    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null; // Employee not found
    }

    // Method to traverse and print all employees
    public void traverseEmployees() {
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }

    // Method to delete an employee by employeeId
    public boolean deleteEmployee(String employeeId) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                employees[i] = employees[count - 1]; // Move the last employee to the deleted spot
                employees[count - 1] = null; // Nullify the last spot
                count--;
                return true;
            }
        }
        return false; // Employee not found
    }

    // Method to update an employee's information
    public boolean updateEmployee(String employeeId, String name, String position, double salary) {
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                employees[i] = new Employee(employeeId, name, position, salary);
                return true;
            }
        }
        return false; // Employee not found
    }
}
public class EmployeeManagement {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);

        // Adding employees
        ems.addEmployee(new Employee("E001", "Alice", "Manager", 75000));
        ems.addEmployee(new Employee("E002", "Bob", "Developer", 55000));
        ems.addEmployee(new Employee("E003", "Charlie", "Designer", 50000));

        // Traverse and print all employees
        System.out.println("All Employees:");
        ems.traverseEmployees();

        // Search for an employee
        System.out.println("\nSearch Employee E002:");
        Employee emp = ems.searchEmployee("E002");
        System.out.println(emp != null ? emp : "Employee not found");

        // Update an employee
        System.out.println("\nUpdating Employee E002:");
        ems.updateEmployee("E002", "Bob", "Senior Developer", 60000);
        ems.traverseEmployees();

        // Delete an employee
        System.out.println("\nDeleting Employee E003:");
        ems.deleteEmployee("E003");
        ems.traverseEmployees();
    }
}
