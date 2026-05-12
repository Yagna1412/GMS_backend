// DashboardBookingRepository.java
package com.gms.backend.customer.dashboard.repository;

import com.gms.backend.customer.dashboard.entity.DashboardBooking;
import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DashboardBookingRepository extends JpaRepository<DashboardBooking, Long> {
    int countByCustomerAndStatus(DashboardCustomer customer, String status);
}