package com.gms.backend.customer.invoices.repository;

import com.gms.backend.customer.invoices.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    // ✅ FIXED: use totalAmount instead of amount
    @Query("SELECT COALESCE(SUM(i.totalAmount), 0) FROM Invoice i WHERE i.status = 'PENDING'")
    Double getTotalDue();

    @Query("SELECT COALESCE(SUM(i.totalAmount), 0) FROM Invoice i WHERE i.status = 'PAID'")
    Double getTotalPaid();
}