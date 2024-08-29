package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.model.Customer;
import com.example.bookstoreapi.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        // Clean up the database before each test
        customerRepository.deleteAll();
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = new Customer(null, "Jane Doe", "jane@example.com", "password");

        mockMvc.perform(post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Jane Doe\", \"email\":\"jane@example.com\", \"password\":\"password\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.email").value("jane@example.com"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        Customer customer = customerRepository.save(new Customer(null, "John Doe", "john@example.com", "password"));

        mockMvc.perform(get("/api/customers/" + customer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        Customer customer = customerRepository.save(new Customer(null, "Old Name", "old@example.com", "password"));

        mockMvc.perform(put("/api/customers/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Name\", \"email\":\"updated@example.com\", \"password\":\"newpassword\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Name"))
                .andExpect(jsonPath("$.email").value("updated@example.com"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        Customer customer = customerRepository.save(new Customer(null, "Name to Delete", "delete@example.com", "password"));
        
        mockMvc.perform(delete("/api/customers/" + customer.getId())).andExpect(status().isNoContent());
    }
}
