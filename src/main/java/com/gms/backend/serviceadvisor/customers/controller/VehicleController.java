package com.gms.backend.serviceadvisor.customers.controller;

import com.gms.backend.serviceadvisor.customers.dto.CustomerDTO;
import com.gms.backend.serviceadvisor.customers.entity.Vehicle;
import com.gms.backend.serviceadvisor.customers.service.CustomerManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private CustomerManagementService customerManagementService;

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(
            @RequestBody CustomerDTO.VehicleRequest request) {

        return ResponseEntity.ok(
                customerManagementService.addVehicle(request)
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Vehicle>> getVehicles(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                customerManagementService.getVehiclesByCustomerId(customerId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        customerManagementService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}