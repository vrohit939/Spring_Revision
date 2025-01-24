package com.example.relationship.onetoone.service;

import com.example.annotation.config.AppConfig;
import com.example.relationship.onetoone.model.Passport;
import com.example.relationship.onetoone.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)  // Tells JUnit 5 to use Spring for the test
@ContextConfiguration(classes = {AppConfig.class})  // Specify Spring configuration class
public class UserServiceTest {

    @Autowired
    private UserService userService;

    /*
     *  Below function is used to test One-to-One Relationship
     *
     */
    @Test
    public void testSaveEmployee() {
        // Create a Passport object
        Passport passport = new Passport("P12345678");

        // Create an User object and associate it with the passport
        User user = new User("John Doe", passport);

        // Save the employee (which will also save the passport)
        userService.saveUser(user);

        // Retrieve the employee from the database by ID
        User retrievedEmployee = userService.findUserById(user.getId());

        // Assertions to verify the one-to-one relationship
        assertNotNull(retrievedEmployee);
        assertNotNull(retrievedEmployee.getPassport());
        assertEquals("John Doe", retrievedEmployee.getName());
        assertEquals("P12345678", retrievedEmployee.getPassport().getPassportNumber());

        // Update employee
        userService.updateUser(user.getId(), "Johnny", "P1234567890");

        // Retrieve the updated employee from the database by ID
        retrievedEmployee = userService.findUserById(user.getId());

        // Assertions to verify the one-to-one relationship
        assertNotNull(retrievedEmployee);
        assertNotNull(retrievedEmployee.getPassport());
        assertEquals("Johnny", retrievedEmployee.getName());
        assertEquals("P1234567890", retrievedEmployee.getPassport().getPassportNumber());

        // Delete User
        userService.deleteUser(user.getId());

        // Retrieve the updated employee from the database by ID
        retrievedEmployee = userService.findUserById(user.getId());

        // Assertions to verify the one-to-one relationship
        assertNull(retrievedEmployee);
    }

}
