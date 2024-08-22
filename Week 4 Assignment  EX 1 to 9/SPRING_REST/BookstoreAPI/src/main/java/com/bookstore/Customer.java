package com.bookstore;

import lombok.Data;

@Data
public class Customer {
    private Long id;
    private String name;
    private String email;
    private String password;

    public Customer(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
