//// DashboardInvoiceRepository.java
//package com.gms.backend.customer.dashboard.repository;
//
//import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
//import com.gms.backend.customer.dashboard.entity.DashboardInvoice;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface DashboardInvoiceRepository extends JpaRepository<DashboardInvoice, Long> {
//
//    @Query("SELECT COALESCE(SUM(i.totalAmount), 0.0) FROM DashboardInvoice i WHERE i.customer = :customer")
//    Double sumTotalAmountByCustomer(@Param("customer") DashboardCustomer customer);
//
//    @Query("""
//        SELECT FUNCTION('MONTH', i.date), SUM(i.totalAmount)
//        FROM DashboardInvoice i
//        WHERE i.customer = :customer
//        GROUP BY FUNCTION('MONTH', i.date)
//    """)
//    List<Object[]> getMonthlySpending(@Param("customer") DashboardCustomer customer);
//}

package com.gms.backend.customer.dashboard.repository;

import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
import com.gms.backend.customer.dashboard.entity.DashboardInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DashboardInvoiceRepository
        extends JpaRepository<DashboardInvoice, Long> {

    // TOTAL SPENT
    @Query("""
        SELECT COALESCE(SUM(i.totalAmount), 0)
        FROM DashboardInvoice i
        WHERE i.customer = :customer
    """)
    Double sumTotalAmountByCustomer(
            @Param("customer") DashboardCustomer customer
    );

    // MONTHLY SPENDING HISTORY
    @Query("""
        SELECT FUNCTION('MONTH', i.date),
               SUM(i.totalAmount)
        FROM DashboardInvoice i
        WHERE i.customer = :customer
        GROUP BY FUNCTION('MONTH', i.date)
        ORDER BY FUNCTION('MONTH', i.date)
    """)
    List<Object[]> getMonthlySpending(
            @Param("customer") DashboardCustomer customer
    );
}