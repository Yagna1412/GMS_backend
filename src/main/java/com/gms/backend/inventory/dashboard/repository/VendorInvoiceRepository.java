package com.gms.backend.inventory.dashboard.repository;

import com.gms.backend.inventory.dashboard.entity.VendorInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorInvoiceRepository extends JpaRepository<VendorInvoice, Long> {

    Long countByPaymentStatus(String paymentStatus);
}
