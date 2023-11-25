package com.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private String name;
    private String employeeId;

    public Employee(String name, String employeeId) {
        this.name = name;
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", employeeId='" + employeeId + '\'' +
                '}';
    }
}

class Department {
    private String departmentName;
    private String departmentId;
    private List<Employee> employees;

    public Department(String departmentName, String departmentId) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
        this.employees = new ArrayList<>();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentName='" + departmentName + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", employees=" + employees +
                '}';
    }
}

public class EmployeeManagementSystemMain {
    private Map<String, Department> departments;

    public EmployeeManagementSystemMain() {
        this.departments = new HashMap<>();
    }

    public void addEmployee(String name, String employeeId, String departmentName, String departmentId) {
        Employee employee = new Employee(name, employeeId);
        Department department = departments.get(departmentId);

        if (department == null) {
            department = new Department(departmentName, departmentId);
            departments.put(departmentId, department);
        }

        department.addEmployee(employee);
    }

    public void updateEmployee(String employeeId, String newName) {
        for (Department department : departments.values()) {
            for (Employee employee : department.getEmployees()) {
                if (employee.getEmployeeId().equals(employeeId)) {
                    employee = new Employee(newName, employee.getEmployeeId());
                }
            }
        }
    }

    public void deleteEmployee(String employeeId) {
        for (Department department : departments.values()) {
            department.getEmployees().removeIf(employee -> employee.getEmployeeId().equals(employeeId));
        }
    }

    public void displayAllEmployees() {
        System.out.println("All Employees:");

        boolean employeesFound = false;

        for (Department department : departments.values()) {
            for (Employee employee : department.getEmployees()) {
                System.out.println(employee);
                employeesFound = true;
            }
        }

        if (!employeesFound) {
            System.out.println("No employees found.");
        }
    }

    public void displayEmployeesInDepartment(String departmentId) {
        Department department = departments.get(departmentId);

        if (department != null) {
            System.out.println("Employees in Department " + department.getDepartmentName() + ":");
            for (Employee employee : department.getEmployees()) {
                System.out.println(employee);
            }
        } else {
            System.out.println("Department not found.");
        }
    }

    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        EmployeeManagementSystemMain system = new EmployeeManagementSystemMain();

        while (true) {
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Display Employees in Department");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter employee ID: ");
                    String employeeId = scanner.nextLine();
                    System.out.print("Enter department name: ");
                    String departmentName = scanner.nextLine();
                    System.out.print("Enter department ID: ");
                    String departmentId = scanner.nextLine();

                    system.addEmployee(name, employeeId, departmentName, departmentId);
                    System.out.println("Employee added successfully.");
                    break;

                case 2:
                    System.out.print("Enter employee ID to update: ");
                    String idToUpdate = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();

                    system.updateEmployee(idToUpdate, newName);
                    System.out.println("Employee updated successfully.");
                    break;

                case 3:
                    System.out.print("Enter employee ID to delete: ");
                    String idToDelete = scanner.nextLine();

                    system.deleteEmployee(idToDelete);
                    System.out.println("Employee deleted successfully.");
                    break;

                case 4:
                    system.displayAllEmployees();
                    break;

                case 5:
                    System.out.print("Enter department ID to display employees: ");
                    String departmentIdToDisplay = scanner.nextLine();

                    system.displayEmployeesInDepartment(departmentIdToDisplay);
                    break;

                case 6:
                    System.out.println("Exiting program.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
