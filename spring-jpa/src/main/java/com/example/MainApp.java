package com.example;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        // Create and Save Employee
        Employee emp = new Employee();
        emp.setName("John Doe");
        emp.setDepartment("IT");
        emp.setSalary(50000);
        employeeService.saveEmployee(emp);
        System.out.println("Employee Saved!");

        // Retrieve Employee
        Employee retrievedEmp = employeeService.getEmployeeById(emp.getId());
        System.out.println("Retrieved Employee: " + retrievedEmp.getName());

        // Update Employee Salary
        employeeService.updateEmployeeSalary(emp.getId(), 60000);
        System.out.println("Updated Salary!");

        // Get All Employees
        List<Employee> employees = employeeService.getAllEmployees();
        employees.forEach(e -> System.out.println("Employee: " + e.getName()));

        // Delete Employee
        employeeService.deleteEmployeeById(emp.getId());
        System.out.println("Employee Deleted!");

        context.close();

    }

}
