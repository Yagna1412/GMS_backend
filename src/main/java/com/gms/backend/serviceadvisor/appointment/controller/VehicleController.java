package com.gms.backend.serviceadvisor.appointment.controller;

import com.gms.backend.serviceadvisor.appointment.model.Vehicle;
import com.gms.backend.serviceadvisor.appointment.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleRepository repository;

    @GetMapping
    public List<Vehicle> getByCustomer(@RequestParam Long customerId) {
        return repository.findByCustomerId(customerId);
    }
}