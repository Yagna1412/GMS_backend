package com.gms.backend.customer.invoices.Controller;

import com.gms.backend.customer.invoices.dto.InvoiceDetailDto;
import com.gms.backend.customer.invoices.dto.InvoiceListDto;
import com.gms.backend.customer.invoices.dto.PaymentRequestDto;
import com.gms.backend.customer.invoices.dto.SummaryDto;
import com.gms.backend.customer.invoices.entity.Invoice;
import com.gms.backend.customer.invoices.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")

public class InvoiceController {

    @Autowired
    private InvoiceService service;

    //  Summary
    @GetMapping("/summary")
    public SummaryDto getSummary() {
        return service.getSummary();
    }



    // UI Table
    @GetMapping
    public List<InvoiceListDto> getAll() {
        return service.getAllInvoices();
    }

    // Download single invoice
    @GetMapping("/{id}/download")
    public InvoiceDetailDto getById(@PathVariable Long id) {
        return service.getInvoiceById(id);
    }

    // Download statement (all invoices)
    @GetMapping("/statement")
    public List<InvoiceDetailDto> getStatement() {
        return service.getStatement();
    }


    //  Pay Invoice
    @PostMapping("/{id}/pay")
    public Invoice pay(@PathVariable Long id,
                       @RequestBody PaymentRequestDto dto) {

        return service.payInvoice(id, dto.getPaymentMethod());
    }


}