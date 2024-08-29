package com.example.bookstoreapi.service;

import com.example.bookstoreapi.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Customer createCustomer(Customer customer);
    Optional<Customer> updateCustomer(Long id, Customer updatedCustomer);
    void deleteCustomer(Long id);
}
