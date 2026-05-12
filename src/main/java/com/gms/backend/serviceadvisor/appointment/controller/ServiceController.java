package com.gms.backend.serviceadvisor.appointment.controller;

import com.gms.backend.serviceadvisor.appointment.model.ServiceItem;
import com.gms.backend.serviceadvisor.appointment.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private ServiceRepository repository;

    @GetMapping
    public List<ServiceItem> getAll() {
        return repository.findAll();
    }
}