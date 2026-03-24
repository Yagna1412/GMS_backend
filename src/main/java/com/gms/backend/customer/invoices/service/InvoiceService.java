package com.gms.backend.customer.invoices.service;

import com.gms.backend.customer.invoices.dto.InvoiceDetailDto;
import com.gms.backend.customer.invoices.dto.InvoiceListDto;
import com.gms.backend.customer.invoices.entity.Invoice;
import com.gms.backend.customer.invoices.repository.InvoiceRepository;
import com.gms.backend.customer.invoices.dto.SummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repo;

    // ✅ 1. Summary
    public SummaryDto getSummary() {

        List<Invoice> invoices = repo.findAll();

        double totalDue = invoices.stream()
                .filter(i -> "PENDING".equalsIgnoreCase(i.getStatus()))
                .mapToDouble(Invoice::getTotalAmount)
                .sum();

        double totalPaid = invoices.stream()
                .filter(i -> "PAID".equalsIgnoreCase(i.getStatus()))
                .mapToDouble(Invoice::getTotalAmount)
                .sum();

        double credit = 0.0; // as per your requirement

        return new SummaryDto(totalDue, totalPaid, credit);
    }


    //invoice list
    public List<InvoiceListDto> getAllInvoices() {
        return repo.findAll().stream()
                .map(i -> new InvoiceListDto(
                        i.getInvoiceNumber(),
                        i.getServiceDate(),
                        i.getTotalAmount(),
                        i.getStatus()
                ))
                .toList();
    }

    //  Download Invoice
    public InvoiceDetailDto getInvoiceById(Long id) {
        Invoice invoice = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        return new InvoiceDetailDto(invoice);
    }

    //  Pay Invoice
    public Invoice payInvoice(Long id, String method) {

        Invoice invoice = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        // Prevent double payment
        if ("PAID".equalsIgnoreCase(invoice.getStatus())) {
            throw new RuntimeException("Invoice already paid");
        }

        // Validate input
        if (method == null || method.isBlank()) {
            throw new RuntimeException("Payment method is required");
        }

        // Update payment details
        invoice.setStatus("PAID");
        invoice.setPaymentMethod(method);
        invoice.setPaymentDate(LocalDateTime.now().toString());

        return repo.save(invoice);
    }

    //  Statement
    public List<InvoiceDetailDto> getStatement() {
        return repo.findAll().stream()
                .map(InvoiceDetailDto::new)
                .toList();
    }
}