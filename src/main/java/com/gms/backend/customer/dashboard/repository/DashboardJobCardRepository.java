
package com.gms.backend.customer.dashboard.repository;

import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
import com.gms.backend.customer.dashboard.entity.DashboardJobCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardJobCardRepository extends JpaRepository<DashboardJobCard, Long> {

    int countByCustomerAndStatus(DashboardCustomer customer, String status);

    List<DashboardJobCard> findTop5ByCustomerOrderByIdDesc(DashboardCustomer customer);

    @Query("""
        SELECT j.serviceType, COUNT(j)
        FROM DashboardJobCard j
        WHERE j.customer = :customer
        GROUP BY j.serviceType
    """)
    List<Object[]> getServiceBreakdown(@Param("customer") DashboardCustomer customer);

    List<DashboardJobCard> findAll();
}