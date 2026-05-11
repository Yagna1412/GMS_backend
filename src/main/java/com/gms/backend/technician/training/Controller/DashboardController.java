package com.gms.backend.technician.training.Controller;

import com.gms.backend.technician.training.Dto.DashboardDTO;
import com.gms.backend.technician.training.Service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    // Dashboard Summary
    @GetMapping("/summary/{userId}")
    public DashboardDTO getSummary(
            @PathVariable Long userId
    ) {
        return service.getSummary(userId);
    }
}