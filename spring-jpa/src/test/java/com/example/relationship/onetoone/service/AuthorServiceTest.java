package com.example.relationship.onetoone.service;

import com.example.annotation.config.AppConfig;
import com.example.relationship.onetomany.model.Author;
import com.example.relationship.onetomany.model.Book;
import com.example.relationship.onetomany.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    public void testAuthorAndBookRelationShip() {

        // Save Author and Book
        Author author = new Author("Java 8");

        // Create Books
        Book book1 = new Book("Java programming", author);
        Book book2 = new Book("Hibernate programming", author);

        author.setBooks(Arrays.asList(book1, book2));

        authorService.saveAuthorWithBooks(author);

        // Fetch Books
        List<Book> books = authorService.fetchAllBooksByAuthor(author.getName());
        Assertions.assertNotNull(books);

        // Delete Author
        authorService.deleteAuthor(author.getName());

        // Fetch Books
        books = authorService.fetchAllBooksByAuthor(author.getName());
        Assertions.assertTrue(books.isEmpty());
    }

}
