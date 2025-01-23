package com.example.xml;

import com.example.xml.model.Employee;
import com.example.xml.service.EmployeeService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        // Create and Save Employees
        Employee emp = new Employee();
        emp.setName("John Doe");
        emp.setDepartment("IT");
        emp.setSalary(50000);
        employeeService.saveEmployee(emp);
        System.out.println("Employees Saved!");

        // Retrieve Employees
        Employee retrievedEmp = employeeService.getEmployeeById(emp.getId());
        System.out.println("Retrieved Employees: " + retrievedEmp.getName());

        // Update Employees Salary
        employeeService.updateEmployeeSalary(emp.getId(), 60000);
        System.out.println("Updated Salary!");

        // Get All Employees
        List<Employee> employees = employeeService.getAllEmployees();
        employees.forEach(e -> System.out.println("Employees: " + e.getName()));

        // Delete Employees
        employeeService.deleteEmployeeById(emp.getId());
        System.out.println("Employees Deleted!");

        context.close();

    }

}
