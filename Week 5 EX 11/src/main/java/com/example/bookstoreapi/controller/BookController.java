package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookResourceAssembler bookResourceAssembler;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return ResponseEntity.ok(bookResourceAssembler.toModel(book));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Book>>> getAllBooks() {
        List<EntityModel<Book>> books = bookService.getAllBooks().stream()
                .map(bookResourceAssembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Book>> createBook(@RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        EntityModel<Book> bookResource = bookResourceAssembler.toModel(createdBook);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/books/" + createdBook.getId());
        return new ResponseEntity<>(bookResource, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(id, updatedBook)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
        return ResponseEntity.ok(bookResourceAssembler.toModel(book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
