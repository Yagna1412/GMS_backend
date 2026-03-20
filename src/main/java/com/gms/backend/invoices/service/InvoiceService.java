package com.gms.backend.invoices.service;

import com.gms.backend.invoices.dto.Dto;
import com.gms.backend.invoices.entity.Invoice;
import com.gms.backend.invoices.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repo;

    // 1. Summary
    public Dto getSummary() {
        List<Invoice> invoices = repo.findAll();

        double totalPaid = invoices.stream()
                .filter(i -> "PAID".equalsIgnoreCase(i.getStatus()))
                .mapToDouble(i -> i.getAmount() != null ? i.getAmount() : 0.0)
                .sum();

        double totalDue = invoices.stream()
                .filter(i -> "PENDING".equalsIgnoreCase(i.getStatus()))
                .mapToDouble(i -> i.getAmount() != null ? i.getAmount() : 0.0)
                .sum();

        return new Dto(totalDue, totalPaid, 0.0);
    }

    // 2. Get All
    public List<Invoice> getAllInvoices() {
        return repo.findAll();
    }


}