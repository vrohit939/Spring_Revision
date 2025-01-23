package com.example.annotation.dao;

import com.example.annotation.model.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    private EmployeeDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void saveEmployee(Employees employee) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Employees getEmployeeById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Employees employee = entityManager.find(Employees.class, id);
        entityManager.close();
        return employee;
    }

    public List<Employees> getAllEmployees() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Employees> employees = entityManager.createQuery("FROM Employees", Employees.class).getResultList();
        entityManager.close();
        return employees;
    }

    public void updateEmployeeSalary(int id, double salary) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employees employee = entityManager.find(Employees.class, id);
        if (employee != null) {
            employee.setSalary(salary);
            entityManager.merge(employee);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void deleteEmployeeById(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employees employee = entityManager.find(Employees.class, id);
        if (employee != null) {
            entityManager.remove(employee);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
