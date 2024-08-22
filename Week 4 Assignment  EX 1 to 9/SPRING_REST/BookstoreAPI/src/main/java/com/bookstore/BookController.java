package com.bookstore;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController() {
        // Sample books added to the list for demonstration
        books.add(new Book(1L, "Spring in Action", "Craig Walls", 39.99, "9781617294945"));
        books.add(new Book(2L, "Effective Java", "Joshua Bloch", 45.00, "9780134685991"));
    }

    // Fetch all books
    @GetMapping
    public List<Book> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        return books.stream()
                .filter(book -> (title == null || book.getTitle().contains(title)) &&
                                (author == null || book.getAuthor().contains(author)))
                .collect(Collectors.toList());
    }

    // Fetch a book by ID (Path Variable)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book getBookById(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    }
 // Fetch all books with custom headers
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomValue");
        return new ResponseEntity<>(books, headers, HttpStatus.OK);
    }


    // Create a new book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook1(@RequestBody Book book) {
        books.add(book);
        return book;
    }
    // Create a new book with custom headers
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        books.add(book);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/books/" + book.getId());
        return new ResponseEntity<>(book, headers, HttpStatus.CREATED);
    }

    // Update a book by ID
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());
                return book;
            }
        }
        throw new RuntimeException("Book not found with id " + id);
    }
 // Update a book by ID with custom headers
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook1(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setPrice(updatedBook.getPrice());
                book.setIsbn(updatedBook.getIsbn());
                
                HttpHeaders headers = new HttpHeaders();
                headers.add("Custom-Header", "UpdatedValue");
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
            }
        }
        throw new BookNotFoundException("Book not found with id " + id);
    }


    // Delete a book by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook1(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "DeletedValue");
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }
}
