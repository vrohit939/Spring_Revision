package com.example.dao;

import com.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final SessionFactory sessionFactory;

    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        getCurrentSession().saveOrUpdate(employee);
    }

    @Transactional(readOnly = true)
    public Employee getEmployeeById(int id) {
        return getCurrentSession().get(Employee.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return getCurrentSession().createQuery("from Employee", Employee.class).list();
    }

    @Override
    public void updateEmployeeSalary(int id, double salary) {

    }

    @Override
    @Transactional
    public void deleteEmployeeById(int id) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            getCurrentSession().delete(employee);
        }
    }
}
