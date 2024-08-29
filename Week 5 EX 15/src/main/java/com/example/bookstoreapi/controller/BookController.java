package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.service.BookService;
import com.example.bookstoreapi.exception.BookNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Get a book by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the book"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Operation(summary = "Get all books")
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }

    @Operation(summary = "Create a new book")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Book created"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/books/" + createdBook.getId());
        return new ResponseEntity<>(createdBook, headers, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing book")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book updated"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(id, updatedBook)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @Operation(summary = "Delete a book by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Book deleted"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
