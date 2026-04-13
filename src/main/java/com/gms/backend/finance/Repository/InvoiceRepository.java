package com.gms.backend.finance.Repository;

import com.gms.backend.customer.Customer;
import com.gms.backend.finance.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    @Query("SELECT COALESCE(SUM(i.totalAmount), 0.0) FROM Invoice i WHERE i.customer = :customer")
    Double sumTotalAmountByCustomer(@Param("customer") Customer customer);

    // 🔥 NEW: Monthly Spending
    @Query("""
        SELECT FUNCTION('MONTH', i.date), SUM(i.totalAmount)
        FROM Invoice i
        WHERE i.customer = :customer
        GROUP BY FUNCTION('MONTH', i.date)
    """)
    List<Object[]> getMonthlySpending(@Param("customer") Customer customer);
}