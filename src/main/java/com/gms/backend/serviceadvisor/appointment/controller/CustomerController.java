package com.gms.backend.serviceadvisor.appointment.controller;

import com.gms.backend.serviceadvisor.appointment.model.Customer;
import com.gms.backend.serviceadvisor.appointment.repository.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @GetMapping
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Customer create(@Valid @RequestBody Customer customer) {
        return repository.save(customer);
    }
}