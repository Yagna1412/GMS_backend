package com.gms.backend.invoices.repository;

import com.gms.backend.invoices.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {
}
