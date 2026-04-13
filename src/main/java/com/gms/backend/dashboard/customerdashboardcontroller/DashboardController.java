package com.gms.backend.dashboard.customerdashboardcontroller;

import com.gms.backend.customer.Customer;
import com.gms.backend.customer.dashboardcustomerrepository.CustomerRepository;
import com.gms.backend.dashboard.customerdashboarddto.*;
import com.gms.backend.dashboard.customerdashboardservice.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;
    private final CustomerRepository customerRepo;

    public DashboardController(DashboardService service, CustomerRepository customerRepo) {
        this.service = service;
        this.customerRepo = customerRepo;
    }

    private Customer getUser() {
        return customerRepo.findById(1L).orElse(null);
    }

    @GetMapping("/summary")
    public DashboardSummaryDTO getSummary() {
        return service.getSummary(getUser());
    }

    @GetMapping("/spending-history")
    public List<SpendingHistoryDTO> getHistory() {
        return service.getHistory(getUser());
    }

    @GetMapping("/service-breakdown")
    public List<ServiceBreakdownDTO> getBreakdown() {
        return service.getBreakdown(getUser());
    }
}