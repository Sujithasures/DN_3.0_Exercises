package com.bookstore.service;

import com.bookstore.Book;
import com.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Create a new book
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // Retrieve a book by ID
    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
    }

    // Update an existing book
    public Book updateBook(Long id, Book book) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found with id " + id);
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    // Delete a book by ID
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Book not found with id " + id);
        }
        bookRepository.deleteById(id);
    }

    // Retrieve all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
