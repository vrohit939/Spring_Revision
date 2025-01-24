package com.example.relationship.onetomany.service;

import com.example.relationship.onetomany.model.Author;
import com.example.relationship.onetomany.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveAuthorWithBooks(Author author) {
        entityManager.persist(author);
    }

    public List<Book> fetchAllBooksByAuthor(String authorName) {
        return entityManager.createQuery("SELECT b FROM Book b where b.author.name = :authorName")
                .setParameter("authorName", authorName)
                .getResultList();
    }

    @Transactional
    public void deleteAuthor(String authorName) {
        // Fetch the author first
        Author author = entityManager.createQuery(
                        "SELECT a FROM Author a WHERE a.name = :name", Author.class)
                .setParameter("name", authorName)
                .getSingleResult();

        if (author != null) {
            entityManager.remove(author); // Deletes author and cascades to books
        }
    }

}
