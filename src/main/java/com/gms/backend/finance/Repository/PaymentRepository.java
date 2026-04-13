package com.gms.backend.finance.Repository;

import com.gms.backend.finance.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
