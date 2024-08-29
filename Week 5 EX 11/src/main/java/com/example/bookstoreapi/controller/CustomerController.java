package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerResourceAssembler customerResourceAssembler;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Customer>> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return ResponseEntity.ok(customerResourceAssembler.toModel(customer));
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Customer>>> getAllCustomers() {
        List<EntityModel<Customer>> customers = customerService.getAllCustomers().stream()
                .map(customerResourceAssembler::toModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Customer>> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        EntityModel<Customer> customerResource = customerResourceAssembler.toModel(createdCustomer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/customers/" + createdCustomer.getId());
        return new ResponseEntity<>(customerResource, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Customer>> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(id, updatedCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
        return ResponseEntity.ok(customerResourceAssembler.toModel(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
