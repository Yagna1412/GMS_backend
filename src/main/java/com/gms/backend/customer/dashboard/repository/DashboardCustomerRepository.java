
package com.gms.backend.customer.dashboard.repository;

import com.gms.backend.customer.dashboard.entity.DashboardCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DashboardCustomerRepository extends JpaRepository<DashboardCustomer, Long> {
    Optional<DashboardCustomer> findByEmail(String email);
}