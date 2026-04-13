package com.gms.backend.jobcard.controller;

import com.gms.backend.customer.Customer;
import com.gms.backend.customer.dashboardcustomerrepository.CustomerRepository;
import com.gms.backend.jobcard.JobCard;
import com.gms.backend.jobcard.Repository.JobCardRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/service")
public class ServiceBookingController {

    private final JobCardRepository repo;
    private final CustomerRepository customerRepo;

    public ServiceBookingController(JobCardRepository repo, CustomerRepository customerRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
    }

    @PostMapping("/book")
    public String bookService(@RequestBody JobCard request) {

        Customer user = customerRepo.findById(1L).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        request.setCustomer(user);
        request.setStatus("Booked");
        request.setDate(LocalDate.now());

        repo.save(request);

        return "Service Booked Successfully";
    }
}