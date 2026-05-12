package com.gms.backend.customer.dashboard.controller;

import com.gms.backend.customer.dashboard.dto.*;
import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
import com.gms.backend.customer.dashboard.repository.DashboardCustomerRepository;
import com.gms.backend.customer.dashboard.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;
    private final DashboardCustomerRepository customerRepo;

    public DashboardController(DashboardService service, DashboardCustomerRepository customerRepo) {
        this.service = service;
        this.customerRepo = customerRepo;
    }

    private DashboardCustomer getCurrentUser() {
        return customerRepo.findById(1L).orElse(null);
    }

    // 1. SUMMARY
    @GetMapping("/summary")
    public DashboardSummaryDTO getSummary() {
        return service.getSummary(getCurrentUser());
    }

    // 2. SPENDING HISTORY
    @GetMapping("/spending-history")
    public List<SpendingHistoryDTO> getHistory() {
        return service.getHistory(getCurrentUser());
    }

    // 3. SERVICE BREAKDOWN
    @GetMapping("/service-breakdown")
    public List<ServiceBreakdownDTO> getBreakdown() {
        return service.getBreakdown(getCurrentUser());
    }
}