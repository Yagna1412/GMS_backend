package com.gms.backend.customer.invoices.service;

import com.gms.backend.customer.invoices.dto.InvoiceDetailDto;
import com.gms.backend.customer.invoices.dto.InvoiceListDto;
import com.gms.backend.customer.invoices.entity.Invoices;
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
        List<Invoices> invoices = repo.findAll();

        if (invoices == null || invoices.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Invoices found");
        }

        double totalDue = invoices.stream()
                .filter(i -> "PENDING".equalsIgnoreCase(i.getStatus()))
                .mapToDouble(Invoices::getTotalAmount)
                .sum();

        double totalPaid = invoices.stream()
                .filter(i -> "PAID".equalsIgnoreCase(i.getStatus()))
                .mapToDouble(Invoices::getTotalAmount)
                .sum();

        double credit = 0.0;

        return new SummaryDto(totalDue, totalPaid, credit);
    }

    // Invoices list
    public List<InvoiceListDto> getAllInvoices() {
        List<Invoices> invoices = repo.findAll();

        if (invoices == null || invoices.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Invoices found");
        }

        return invoices.stream()
                .map(i -> new InvoiceListDto(
                        i.getId(),
                        i.getInvoiceNumber(),
                        i.getServiceDate(),
                        i.getTotalAmount(),
                        i.getStatus()
                ))
                .toList();
    }

    // Download Invoices
    public InvoiceDetailDto getInvoiceById(Long id) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Invoices ID: " + id);
        }

        Invoices invoices = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Invoices not found with ID: " + id));

        return new InvoiceDetailDto(invoices);
    }
    // Pay Invoices
    public Invoices payInvoice(Long id, String method) {
        if (id == null || id <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Invoices ID: " + id);
        }

        Invoices invoices = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Invoices not found with ID: " + id));

        if ("PAID".equalsIgnoreCase(invoices.getStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Invoices with ID " + id + " is already paid");
        }

        if (method == null || method.isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Payment method is required");
        }

        invoices.setStatus("PAID");
        invoices.setPaymentMethod(method);
        invoices.setPaymentDate(LocalDateTime.now().toString());

        return repo.save(invoices);
    }

    // Statement
    public List<InvoiceDetailDto> getStatement() {
        List<Invoices> invoices = repo.findAll();

        if (invoices == null || invoices.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Invoices available for statement");
        }

        return invoices.stream()
                .map(InvoiceDetailDto::new)
                .toList();
    }
}