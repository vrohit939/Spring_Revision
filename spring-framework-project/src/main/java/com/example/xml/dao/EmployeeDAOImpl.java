package com.example.xml.dao;

import com.example.xml.model.Employee;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, employee.getName(), employee.getDepartment(), employee.getSalary());
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public void updateEmployeeSalary(int id, double salary) {
        String sql = "UPDATE employees SET salary = ? WHERE id = ?";
        jdbcTemplate.update(sql, salary, id);
    }

    @Override
    public void deleteEmployeeById(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
