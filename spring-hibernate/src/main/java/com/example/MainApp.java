package com.example;

import com.example.model.Employee;
import com.example.service.EmployeeServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        // Use below if beans are defined through xml

        // Step 1: Load the Spring Application Context
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Step 2: Retrieve the EmployeeService Bean
        EmployeeServiceImpl employeeService = context.getBean("employeeService", EmployeeServiceImpl.class);

        // Step 3: Create a New Employee Object
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment("Engineering");
        employee.setSalary(75000);

        // Step 4: Save the Employee Record
        employeeService.addEmployee(employee);

        // Step 5: Fetch and Display All Employee Records
        System.out.println("Employee List:");
        for (Employee emp : employeeService.listEmployees()) {
            System.out.println(emp.getId() + " " + emp.getName() + " " + emp.getDepartment() + " " + emp.getSalary());
        }

        context.close();

    }
}
