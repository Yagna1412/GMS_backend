package com.gms.backend.serviceadvisor.appointment.controller;

import com.gms.backend.serviceadvisor.appointment.model.Technician;
import com.gms.backend.serviceadvisor.appointment.repository.TechnicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technicians")
public class TechnicianController {
    @Autowired
    private TechnicianRepository repository;

    @GetMapping
    public List<Technician> getAll() {
        return repository.findAll();
    }
}