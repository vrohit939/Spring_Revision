package com.example.relationship.onetoone.model;

import jakarta.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passportNumber;

    @OneToOne(mappedBy = "passport")
    private User user;

    public Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Passport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public User getEmployee() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
