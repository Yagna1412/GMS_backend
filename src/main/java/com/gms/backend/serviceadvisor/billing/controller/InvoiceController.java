package com.gms.backend.serviceadvisor.billing.controller;

import com.gms.backend.serviceadvisor.billing.entity.Invoice;
import com.gms.backend.serviceadvisor.billing.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    // GET ALL + FILTERS + SEARCH
    @GetMapping("/invoices")
    public List<Invoice> getInvoices(

            @RequestParam(required = false) String deliveryStatus,

            @RequestParam(required = false) Boolean deliveredToday,

            @RequestParam(required = false) String paymentStatus,

            @RequestParam(required = false) String query
    ) {

        return service.getAllInvoices(
                deliveryStatus,
                deliveredToday,
                paymentStatus,
                query
        );
    }

    // GET INVOICE BY ID
    @GetMapping("/invoices/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {

        return service.getInvoiceById(id);
    }

    // MARK AS DELIVERED
    @PutMapping("/invoices/{id}/deliver")
    public Invoice markAsDelivered(@PathVariable Long id) {

        return service.markAsDelivered(id);
    }

    // DASHBOARD SUMMARY
    @GetMapping("/billing/summary")
    public InvoiceService.SummaryResponse getSummary() {

        return service.getSummary();
    }
}