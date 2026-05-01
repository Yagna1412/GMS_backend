package com.gms.backend.serviceadvisor.billing.service;

import com.gms.backend.serviceadvisor.billing.entity.Invoice;
import com.gms.backend.serviceadvisor.billing.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repo;

    // GET ALL + FILTERS + SEARCH
    public List<Invoice> getAllInvoices(String deliveryStatus,
                                        Boolean deliveredToday,
                                        String paymentStatus,
                                        String query) {

        // SEARCH
        if (query != null && !query.isEmpty()) {
            return repo.findByCustomerNameContainingIgnoreCase(query);
        }

        // FILTER READY FOR DELIVERY
        if (deliveryStatus != null && !deliveryStatus.isEmpty()) {
            return repo.findByDeliveryStatus(deliveryStatus);
        }

        // FILTER PAYMENT STATUS
        if (paymentStatus != null && !paymentStatus.isEmpty()) {
            return repo.findByPaymentStatus(paymentStatus);
        }

        // FILTER DELIVERED TODAY
        if (deliveredToday != null && deliveredToday) {

            LocalDateTime start = LocalDate.now().atStartOfDay();

            LocalDateTime end = LocalDate.now().atTime(23, 59, 59);

            return repo.findByDeliveredAtBetween(start, end);
        }

        // GET ALL INVOICES
        return repo.findAll();
    }

    // GET INVOICE BY ID
    public Invoice getInvoiceById(Long id) {

        return repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Invoice not found"));
    }

    // MARK AS DELIVERED
    public Invoice markAsDelivered(Long id) {

        Invoice invoice = getInvoiceById(id);

        invoice.setDeliveryStatus("DELIVERED");

        invoice.setDeliveredAt(LocalDateTime.now());

        return repo.save(invoice);
    }

    // DASHBOARD SUMMARY
    public SummaryResponse getSummary() {

        long ready = repo.findByDeliveryStatus("READY").size();

        long deliveredToday = repo.findByDeliveredAtBetween(
                LocalDate.now().atStartOfDay(),
                LocalDate.now().atTime(23, 59, 59)
        ).size();

        long pending = repo.findByPaymentStatus("PENDING").size();

        return new SummaryResponse(
                ready,
                deliveredToday,
                pending
        );
    }

    // SUMMARY RESPONSE CLASS
    public static class SummaryResponse {

        public long readyForDelivery;
        public long deliveredToday;
        public long pendingPayment;

        public SummaryResponse(long ready,
                               long delivered,
                               long pending) {

            this.readyForDelivery = ready;
            this.deliveredToday = delivered;
            this.pendingPayment = pending;
        }
    }
}