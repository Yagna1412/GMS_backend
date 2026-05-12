package com.gms.backend.customer.dashboard.controller;

import com.gms.backend.customer.dashboard.dto.ProfileDTO;
import com.gms.backend.customer.dashboard.dto.VehicleDTO;
import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
import com.gms.backend.customer.dashboard.repository.DashboardCustomerRepository;
import com.gms.backend.customer.dashboard.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final DashboardService service;
    private final DashboardCustomerRepository customerRepo;

    public UserController(DashboardService service, DashboardCustomerRepository customerRepo) {
        this.service = service;
        this.customerRepo = customerRepo;
    }

    private DashboardCustomer getCurrentUser() {
        return customerRepo.findById(1L).orElse(null);
    }

    // 6. VEHICLE DETAILS - Return as ARRAY for frontend
    @GetMapping("/vehicle")
    public List<VehicleDTO> getVehicle() {
        VehicleDTO vehicle = service.getVehicle(getCurrentUser());
        return List.of(vehicle);  // Wrap in list so frontend can do vehicle.data[0]
    }

    // 7. USER INFO
    @GetMapping("/profile")
    public ProfileDTO getProfile() {
        return service.getProfile(getCurrentUser());
    }
}