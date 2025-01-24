package com.example.relationship.manytomany.service;

import com.example.annotation.config.AppConfig;
import com.example.relationship.manytomany.model.Course;
import com.example.relationship.manytomany.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Test
    public void testManyToManyRelationship() {
        Student student = new Student();
        student.setName("Raja");

        Course course1 = new Course();
        course1.setTitle("Java");

        Course course2 = new Course();
        course2.setTitle("Hibernate");

        student.setCourses(Arrays.asList(course1, course2));

        studentService.enrollStudentInCourses(student);

        // Fetch courses of above student
        Student student1 = studentService.fetchStudentByName(student.getName());
        Assertions.assertNotNull(student1.getCourses());

        // Delete student
        studentService.deleteStudentByName(student.getName());

        student1 = studentService.fetchStudentByName(student.getName());
        Assertions.assertNull(student1);

    }

}
