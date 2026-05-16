package com.gms.backend.serviceadvisor.customers.controller;

import com.gms.backend.serviceadvisor.customers.dto.CustomerDTO;
import com.gms.backend.serviceadvisor.customers.entity.CustomerPreference;
import com.gms.backend.serviceadvisor.customers.service.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers/preferences")
public class CustomerPreferenceController {

    @Autowired
    private CustomerManagementService customerManagementService;

    @PostMapping
    public ResponseEntity<?> savePreference(
            @RequestBody CustomerDTO.CustomerPreferenceRequest request) {

        return ResponseEntity.ok(
                customerManagementService.savePreference(request)
        );
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CustomerPreference>> getPreference(@PathVariable Long customerId) {

        return ResponseEntity.ok(customerManagementService.getPreference(customerId));
    }
}