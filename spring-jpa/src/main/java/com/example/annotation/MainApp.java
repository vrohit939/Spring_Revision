package com.example.annotation;

import com.example.annotation.config.AppConfig;
import com.example.annotation.model.Employees;
import com.example.annotation.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EmployeeService employeeService = context.getBean(EmployeeService.class);

        Employees emp = new Employees();
        emp.setName("John Doe");
        emp.setSalary(50000);
        emp.setDepartment("IT");

        employeeService.saveEmployee(emp);
        System.out.println("Employees saved successfully!");

    }

}
