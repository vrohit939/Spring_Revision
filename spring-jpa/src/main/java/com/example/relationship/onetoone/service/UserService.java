package com.example.relationship.onetoone.service;

import com.example.relationship.onetoone.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user); // Persist User with its associated Passport
    }

    @Transactional
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void updateUser(Long id, String name, String passportNumber) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            user.setName(name);
            user.getPassport().setPassportNumber(passportNumber);
            entityManager.merge(user);
        }
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user); // Cascade will remove the passport
        }
    }

}
