package com.bookstore.controller;

import com.bookstore.Assembler.BookResourceAssembler;
import com.bookstore.dto.BookDTO;
import com.bookstore.Book;
import com.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bookstore.mapper.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookResourceAssembler bookResourceAssembler;

	private Object bookMapper;

    @PostMapping
    public ResponseEntity<EntityModel<BookDTO>> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        Book savedBook = bookService.createBook(book);
        BookDTO savedBookDTO = bookMapper.bookToBookDTO(savedBook);
        return ResponseEntity.created(URI.create("/books/" + savedBookDTO.getId()))
                             .body(bookResourceAssembler.toModel(savedBookDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<BookDTO>> getBook(@PathVariable Long id) {
        Book book = bookService.getBook(id);
        BookDTO bookDTO = bookMapper.bookToBookDTO(book);
        return ResponseEntity.ok(bookResourceAssembler.toModel(bookDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<BookDTO>> updateBook(@PathVariable Long id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = bookMapper.bookDTOToBook(bookDTO);
        Book updatedBook = bookService.updateBook(id, book);
        BookDTO updatedBookDTO = bookMapper.bookToBookDTO(updatedBook);
        return ResponseEntity.ok(bookResourceAssembler.toModel(updatedBookDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<BookDTO>>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<EntityModel<BookDTO>> bookDTOs = books.stream()
                                                   .map(book -> bookMapper.bookToBookDTO(book))
                                                   .map(bookResourceAssembler::toModel)
                                                   .collect(Collectors.toList());
        return ResponseEntity.ok(bookDTOs);
    }
}
