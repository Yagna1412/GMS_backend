package com.gms.backend.serviceadvisor.billing.repository;

import com.gms.backend.serviceadvisor.billing.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByDeliveryStatus(String status);

    List<Invoice> findByPaymentStatus(String status);

    List<Invoice> findByCustomerNameContainingIgnoreCase(String name);

    List<Invoice> findByDeliveredAtBetween(LocalDateTime start, LocalDateTime end);
}