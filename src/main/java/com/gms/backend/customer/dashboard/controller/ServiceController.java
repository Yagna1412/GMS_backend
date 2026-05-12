package com.gms.backend.customer.dashboard.controller;

import com.gms.backend.customer.dashboard.dto.RecentServiceDTO;
import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
import com.gms.backend.customer.dashboard.repository.DashboardCustomerRepository;
import com.gms.backend.customer.dashboard.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final DashboardService dashboardService;
    private final DashboardCustomerRepository customerRepo;

    public ServiceController(DashboardService dashboardService, DashboardCustomerRepository customerRepo) {
        this.dashboardService = dashboardService;
        this.customerRepo = customerRepo;
    }

    private DashboardCustomer getCurrentUser() {
        return customerRepo.findById(1L).orElse(null);
    }

    // 5. RECENT SERVICE HISTORY
    @GetMapping("/recent")
    public List<RecentServiceDTO> getRecentServices() {
        return dashboardService.getRecentServices(getCurrentUser());
    }
}