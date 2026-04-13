package com.gms.backend.finance.controller;

import com.gms.backend.finance.Payment;
import com.gms.backend.finance.Repository.PaymentRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentRepository repo;

    public PaymentController(PaymentRepository repo) {
        this.repo = repo;
    }

    @PostMapping("/pay")
    public String pay(@RequestParam double amount) {

        Payment p = new Payment();
        p.setAmount(amount);
        p.setStatus("SUCCESS");

        repo.save(p);

        return "Payment Successful";
    }
}