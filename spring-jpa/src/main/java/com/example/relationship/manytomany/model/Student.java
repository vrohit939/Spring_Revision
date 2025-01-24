package com.example.relationship.manytomany.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_courses",  // Name of the join table
            joinColumns = @JoinColumn(name = "student_id"),  // Foreign key from Student
            inverseJoinColumns = @JoinColumn(name = "course_id")  // Foreign key from Course
    )
    private List<Course> courses = new ArrayList<>();

    // Constructors
    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
