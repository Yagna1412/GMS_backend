package com.gms.backend.jobcard.Repository;

import com.gms.backend.customer.Customer;
import com.gms.backend.jobcard.JobCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobCardRepository extends JpaRepository<JobCard, Long> {

    int countByCustomerAndStatus(Customer customer, String status);

    List<JobCard> findTop5ByCustomerOrderByIdDesc(Customer customer);

    // 🔥 NEW: Service Breakdown
    @Query("""
        SELECT j.serviceType, COUNT(j)
        FROM JobCard j
        WHERE j.customer = :customer
        GROUP BY j.serviceType
    """)
    List<Object[]> getServiceBreakdown(@Param("customer") Customer customer);
    List<JobCard> findTop5ByOrderByIdDesc();
}