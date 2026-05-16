package com.gms.backend.serviceadvisor.customers.controller;

import com.gms.backend.serviceadvisor.customers.entity.ServiceHistory;
import com.gms.backend.serviceadvisor.customers.service.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")

public class ServiceHistoryController {

    @Autowired
    private CustomerManagementService customerManagementService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ServiceHistory>> getServiceHistory(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                customerManagementService.getServiceHistory(customerId)
        );
    }
}