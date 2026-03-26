package com.gms.backend.customer.invoices.service;

import com.gms.backend.customer.invoices.dto.InvoiceDetailDto;
import com.gms.backend.customer.invoices.dto.InvoiceListDto;
import com.gms.backend.customer.invoices.entity.Invoice;
import com.gms.backend.customer.invoices.repository.InvoiceRepository;
import com.gms.backend.customer.invoices.dto.SummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repo;

    // Summary
    public SummaryDto getSummary() {
        List<Invoice> invoices = repo.findAll();

        if (invoices == null || invoices.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No invoices found");
        }

        double totalDue = invoices.stream()
                .filter(i -> "PENDING".equalsIgnoreCase(i.getStatus()))
                .mapToDouble(Invoice::getTotalAmount)
                .sum();

        double totalPaid = invoices.stream()
                .filter(i -> "PAID".equalsIgnoreCase(i.getStatus()))
                .mapToDouble(Invoice::getTotalAmount)
                .sum();

        double credit = 0.0;

        return new SummaryDto(totalDue, totalPaid, credit);
    }

    // Invoice list
    public List<InvoiceListDto> getAllInvoices() {
        List<Invoice> invoices = repo.findAll();

        if (invoices == null || invoices.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No invoices found");
        }

        return invoices.stream()
                .map(i -> new InvoiceListDto(
                        i.getInvoiceNumber(),
                        i.getServiceDate(),
                        i.getTotalAmount(),
                        i.getStatus()
                ))
                .toList();
    }

    // Download Invoice
    public InvoiceDetailDto getInvoiceById(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid invoice ID: " + id);
        }

        Invoice invoice = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Invoice not found with ID: " + id));

        return new InvoiceDetailDto(invoice);
    }
    // Pay Invoice
    public Invoice payInvoice(Long id, String method) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid invoice ID: " + id);
        }

        Invoice invoice = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Invoice not found with ID: " + id));

        if ("PAID".equalsIgnoreCase(invoice.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Invoice with ID " + id + " is already paid");
        }

        if (method == null || method.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Payment method is required");
        }

        invoice.setStatus("PAID");
        invoice.setPaymentMethod(method);
        invoice.setPaymentDate(LocalDateTime.now().toString());

        return repo.save(invoice);
    }

    // Statement
    public List<InvoiceDetailDto> getStatement() {
        List<Invoice> invoices = repo.findAll();

        if (invoices == null || invoices.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No invoices available for statement");
        }

        return invoices.stream()
                .map(InvoiceDetailDto::new)
                .toList();
    }
}