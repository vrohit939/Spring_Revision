package com.example.xml.service;

import com.example.xml.dao.EmployeeDAO;
import com.example.xml.model.Employee;
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

    public void saveEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeDAO.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    public void updateEmployeeSalary(int id, double salary) {
        employeeDAO.updateEmployeeSalary(id, salary);
    }

    public void deleteEmployeeById(int id) {
        employeeDAO.deleteEmployeeById(id);
    }

}
