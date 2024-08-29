package com.example.bookstoreapi.service;

import com.example.bookstoreapi.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();
    
    Optional<Book> getBookById(Long id);

    Book createBook(Book book);

    Optional<Book> updateBook(Long id, Book updatedBook);

    void deleteBook(Long id);

    List<Book> getBooks(String title, String author);
}
