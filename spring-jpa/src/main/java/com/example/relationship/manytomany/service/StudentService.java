package com.example.relationship.manytomany.service;

import com.example.relationship.manytomany.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void enrollStudentInCourses(Student student) {
        entityManager.persist(student);
    }

    public Student fetchStudentByName(String studentName) {
        try {
            return entityManager.createQuery(
                            "SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.name = :studentName", Student.class)   // If we want to avoid join query then eager loading needs to be set
                    .setParameter("studentName", studentName)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void deleteStudentByName(String studentName) {
        Student student = fetchStudentByName(studentName);
        if (student != null) {
            entityManager.remove(student);
        }
    }

}
