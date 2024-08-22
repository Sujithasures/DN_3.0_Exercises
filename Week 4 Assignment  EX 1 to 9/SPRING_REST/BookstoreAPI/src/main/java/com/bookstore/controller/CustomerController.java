package com.bookstore.controller;

import com.bookstore.Assembler.CustomerResourceAssembler;
import com.bookstore.dto.CustomerDTO;
import com.bookstore.Customer;
import com.bookstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerResourceAssembler customerResourceAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<CustomerDTO>> createCustomer(@Valid @RequestBody CustomerDTO customerDTO, Object customerMapper) {
    
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerService.createCustomer(customer);
        CustomerDTO savedCustomerDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        return ResponseEntity.created(URI.create("/customers/" + savedCustomerDTO.getId()))
                             .body(customerResourceAssembler.toModel(savedCustomerDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> getCustomer(@PathVariable Long id, Object customerMapper) {
        Customer customer = customerService.getCustomer(id);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
        return ResponseEntity.ok(customerResourceAssembler.toModel(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> updateCustomer(@PathVariable Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        CustomerDTO updatedCustomerDTO = customerMapper.customerToCustomerDTO(updatedCustomer);
        return ResponseEntity.ok(customerResourceAssembler.toModel(updatedCustomerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<CustomerDTO>>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        List<EntityModel<CustomerDTO>> customerDTOs = customers.stream()
                                                               .map(customer -> customerMapper.customerToCustomerDTO(customer))
                                                               .map(customerResourceAssembler::toModel)
                                                               .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOs);
    }
}
