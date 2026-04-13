package com.gms.backend.dashboard.customerdashboardservice;

import com.gms.backend.customer.Customer;
import com.gms.backend.customer.dashboardcustomerrepository.CustomerRepository;
import com.gms.backend.dashboard.customerdashboarddto.RecentServiceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final DashboardService dashboardService;
    private final CustomerRepository customerRepo;

    public ServiceController(DashboardService dashboardService,
                             CustomerRepository customerRepo) {
        this.dashboardService = dashboardService;
        this.customerRepo = customerRepo;
    }

    // ✅ TEMP USER (since no JWT)
    private Customer getUser() {
        return customerRepo.findById(1L).orElse(null);
    }

    // ✅ ONLY THIS API HERE
    @GetMapping("/recent")
    public List<RecentServiceDTO> getRecentServices() {
        return dashboardService.getRecentServices(getUser());
    }
}