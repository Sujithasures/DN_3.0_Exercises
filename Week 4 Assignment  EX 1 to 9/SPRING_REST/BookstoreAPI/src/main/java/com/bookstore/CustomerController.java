package com.bookstore;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    // 
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
       
        return customer;
    }
    // Method to handle customer registration using form data
    @PostMapping("/form")
    public Customer createCustomerFromForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {
        // Create a new customer object from the form data
        Customer customer = new Customer(null, name, email, password);
        
        return customer;
    }

}
