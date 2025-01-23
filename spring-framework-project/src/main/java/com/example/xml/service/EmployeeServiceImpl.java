package com.example.xml.service;

import com.example.xml.dao.EmployeeDAO;
import com.example.xml.model.Employee;

import java.util.List;

public class EmployeeServiceImpl {

    private EmployeeDAO employeeDAO;

    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void addEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    public List<Employee> listEmployees() {
        return employeeDAO.getAllEmployees();
    }
}
