package com.library.service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.library.service.BookService;

public class LibraryManagement1 {

    public static void main(String[] args) {
        // Load Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get BookService bean and use it
        BookService bookService = context.getBean(BookService.class);

        // Call method to display a message
        bookService.displayMessage();
    }
}
