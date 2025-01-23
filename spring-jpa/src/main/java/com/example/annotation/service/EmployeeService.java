package com.example.annotation.service;

import com.example.annotation.dao.EmployeeDAO;
import com.example.annotation.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public void saveEmployee(Employees employee) {
        employeeDAO.saveEmployee(employee);
    }

    public Employees getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public List<Employees> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public void updateEmployeeSalary(int id, double salary) {
        employeeDAO.updateEmployeeSalary(id, salary);
    }

    public void deleteEmployeeById(int id) {
        employeeDAO.deleteEmployeeById(id);
    }

}
