package com.gms.backend.invoices.Controller;

import com.gms.backend.invoices.dto.Dto;
import com.gms.backend.invoices.entity.Invoice;
import com.gms.backend.invoices.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    // 1. Summary
    @GetMapping("/summary")
    public Dto getSummary() {
        return service.getSummary();
    }

    // 2. Get All
    @GetMapping
    public List<Invoice> getAll() {
        return service.getAllInvoices();
    }

    // 4. Pay Invoice
    @PostMapping("/{id}/pay")
    public Invoice pay(@PathVariable Long id) {
        return service.payInvoice(id);
    }
}