package com.gms.backend.jobcard.controller;

import com.gms.backend.customer.Customer;
import com.gms.backend.customer.dashboardcustomerrepository.CustomerRepository;
import com.gms.backend.jobcard.JobCard;
import com.gms.backend.jobcard.repository.JobCardRepository;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/service")
public class ServiceBookingController {

    private final JobCardRepository repo;
    private final CustomerRepository customerRepo;

    public ServiceBookingController(JobCardRepository repo, CustomerRepository customerRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
    }

    // ✅ BOOK SERVICE
    @PostMapping("/book")
    public String bookService(@RequestBody JobCard request) {

        Customer user = customerRepo.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));

        request.setCustomer(user);
        request.setStatus("Booked");
        request.setScheduledDate(LocalDate.now());

        repo.save(request);

        return "Service Booked Successfully";
    }

    // ✅ RECENT SERVICES (🔥 THIS WAS MISSING)
    @GetMapping("/recent")
    public List<JobCard> getRecentServices() {

        Customer user = customerRepo.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return repo.findTop5ByCustomerOrderByIdDesc(user);
    }
}
