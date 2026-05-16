package com.gms.backend.serviceadvisor.customers.controller;

import com.gms.backend.serviceadvisor.customers.dto.CustomerDTO;
import com.gms.backend.serviceadvisor.customers.entity.Customer;
import com.gms.backend.serviceadvisor.customers.service.CustomerManagementService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerManagementService customerManagementService;

    // STATS
    @GetMapping("/stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(customerManagementService.getCustomerStats());
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String type) {

        return ResponseEntity.ok(
                customerManagementService.getAllCustomers(search, type)
        );
    }

    // CREATE (FIXED)
    @PostMapping
    public ResponseEntity<Customer> createCustomer(
            @Valid @RequestBody CustomerDTO.CustomerRequest request) {

        return ResponseEntity.ok(
                customerManagementService.createCustomer(request)
        );
    }

    // GET FULL PROFILE
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(
                customerManagementService.getCustomerFullProfile(id)
        );
    }

    // UPDATE (FIXED)
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDTO.CustomerRequest request) {

        return ResponseEntity.ok(
                customerManagementService.updateCustomer(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerManagementService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}