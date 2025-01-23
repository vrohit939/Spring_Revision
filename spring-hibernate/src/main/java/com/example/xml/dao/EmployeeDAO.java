package com.example.xml.dao;

import com.example.xml.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    void saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    void updateEmployeeSalary(int id, double salary);

    void deleteEmployeeById(int id);
}
