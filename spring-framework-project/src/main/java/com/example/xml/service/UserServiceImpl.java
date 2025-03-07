package com.example.xml.service;

import com.example.xml.dao.UserDAOImpl;
import com.example.xml.model.User;

import java.util.List;

public class UserServiceImpl {
    private UserDAOImpl userDAO;

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    public User getUserById(int id) {
        return userDAO.getUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUserById(int id) {
        userDAO.deleteUserById(id);
    }
}
