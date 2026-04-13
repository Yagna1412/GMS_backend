package com.gms.backend.dashboard.customerdashboardservice;


import com.gms.backend.jobcard.JobCard; // Or a specific ServiceHistory projection
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServiceHistoryRepository extends JpaRepository<JobCard, Long> {

    @Query("SELECT j FROM JobCard j WHERE j.customer.id = :customerId ORDER BY j.id DESC")
    List<JobCard> findRecentServicesByCustomerId(@Param("customerId") Long customerId);
}