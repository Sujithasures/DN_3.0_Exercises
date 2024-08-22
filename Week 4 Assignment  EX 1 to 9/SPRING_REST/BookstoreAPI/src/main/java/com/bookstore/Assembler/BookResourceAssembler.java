package com.bookstore.Assembler;

import com.bookstore.controller.BookController;
import com.bookstore.dto.BookDTO;
import com.bookstore.Book;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class BookResourceAssembler {

    public EntityModel<BookDTO> toModel(BookDTO bookDTO) {
        EntityModel<BookDTO> bookResource = EntityModel.of(bookDTO);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBook(bookDTO.getId())).withSelfRel();
        Link allBooksLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getAllBooks()).withRel("all-books");

        bookResource.add(selfLink);
        bookResource.add(allBooksLink);

        return bookResource;
    }
}
