package com.example.xml.service;

import com.example.xml.dao.EmployeeDAO;
import com.example.xml.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeDAO employeeDAO;

//    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
//        this.employeeDAO = employeeDAO;
//    }

    public void addEmployee(Employee employee) {
        employeeDAO.saveEmployee(employee);
    }

    public List<Employee> listEmployees() {
        return employeeDAO.getAllEmployees();
    }
}
