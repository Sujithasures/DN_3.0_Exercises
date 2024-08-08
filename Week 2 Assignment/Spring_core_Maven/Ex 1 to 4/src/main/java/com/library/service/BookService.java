package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayMessage() {
        System.out.println("BookService is working!");
       
        String bookInfo = bookRepository.getBookById(1);
        System.out.println(bookInfo);
    }
}