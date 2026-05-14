package com.gms.backend.customer.dashboard.controller;

import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
import com.gms.backend.customer.dashboard.entity.DashboardJobCard;
import com.gms.backend.customer.dashboard.repository.DashboardCustomerRepository;
import com.gms.backend.customer.dashboard.repository.DashboardJobCardRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class QuickActionsController {

    private final DashboardJobCardRepository jobRepo;
    private final DashboardCustomerRepository customerRepo;

    public QuickActionsController(
            DashboardJobCardRepository jobRepo,
            DashboardCustomerRepository customerRepo) {
        this.jobRepo = jobRepo;
        this.customerRepo = customerRepo;
    }

    private DashboardCustomer getCurrentUser() {
        return customerRepo.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // ================== BOOK SERVICE ==================
    // POST /api/dashboard/service/book
    @PostMapping("/service/book")
    public String bookService(
            @RequestBody DashboardJobCard request) {

        DashboardCustomer user = getCurrentUser();
        request.setCustomer(user);
        request.setStatus("Booked");
        request.setDate(LocalDate.now());

        jobRepo.save(request);
        return "Service Booked Successfully";
    }

    // ================== TRACK JOB STATUS ==================
    // GET /api/dashboard/job-status
    @GetMapping("/job-status")
    public List<DashboardJobCard> getJobStatus() {
        return jobRepo.findAll();
    }

    // ================== PAY INVOICE ==================
    // POST /api/dashboard/payment/pay?invoiceId=1001
    @PostMapping("/payment/pay")
    public String payInvoice(
            @RequestParam Long invoiceId) {
        return "Payment processed for invoice: " + invoiceId;
    }
}