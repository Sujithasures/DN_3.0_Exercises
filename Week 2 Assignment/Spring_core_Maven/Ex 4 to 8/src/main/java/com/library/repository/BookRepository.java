package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public String getBookById(int id) {
        return "Book with ID: " + id;
    }
}