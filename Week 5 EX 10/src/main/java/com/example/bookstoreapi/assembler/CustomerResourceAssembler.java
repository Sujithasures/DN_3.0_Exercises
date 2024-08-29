package com.example.bookstoreapi.assembler;

import com.example.bookstoreapi.controller.CustomerController;
import com.example.bookstoreapi.model.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CustomerResourceAssembler {

    public EntityModel<Customer> toModel(Customer customer) {
        EntityModel<Customer> customerResource = EntityModel.of(customer);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel();
        customerResource.add(selfLink);

        Link allCustomersLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CustomerController.class).getAllCustomers()).withRel("all-customers");
        customerResource.add(allCustomersLink);

        return customerResource;
    }
}
