package com.example.dao;

import com.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
//    Uncomment below to use via applicationContext.xml
//
//    private final SessionFactory sessionFactory;
//
//    public EmployeeDAOImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    private Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }
//
//    @Override
//    @Transactional
//    public void saveEmployee(Employee employee) {
//        getCurrentSession().saveOrUpdate(employee);
//    }
//
//    @Transactional(readOnly = true)
//    public Employee getEmployeeById(int id) {
//        return getCurrentSession().get(Employee.class, id);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Employee> getAllEmployees() {
//        return getCurrentSession().createQuery("from Employee", Employee.class).list();
//    }
//
//    @Override
//    public void updateEmployeeSalary(int id, double salary) {
//
//    }
//
//    @Override
//    @Transactional
//    public void deleteEmployeeById(int id) {
//        Employee employee = getEmployeeById(id);
//        if (employee != null) {
//            getCurrentSession().delete(employee);
//        }
//    }

    // Use below to use with AppConfig.java
    @Autowired
    private SessionFactory sessionFactory;

    public void saveEmployee(Employee employee) {
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession(); // Open a new session
            tx = session.beginTransaction(); // Begin the transaction
            session.saveOrUpdate(employee); // Perform the save/update operation
            tx.commit(); // Commit the transaction if successful
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Rollback in case of error
            }
            throw e; // Rethrow the exception after rollback
        } finally {
            if (session != null) {
                session.close(); // Always close the session
            }
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.openSession(); // Open a session
        try {
            return session.createQuery("from Employee", Employee.class).list(); // Query all employees
        } finally {
            session.close(); // Close session
        }
    }

    @Override
    public void updateEmployeeSalary(int id, double salary) {

    }

    @Override
    public void deleteEmployeeById(int id) {

    }

//    @Transactional(rollbackFor = Exception.class)
    // Spring handles commit and rollback automatically when this annotation is used. Default rollback is for unchecked exceptions
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }
}
