// DashboardVehicleRepository.java
package com.gms.backend.customer.dashboard.repository;

import com.gms.backend.customer.dashboard.entity.DashboardVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DashboardVehicleRepository extends JpaRepository<DashboardVehicle, Long> {
    Optional<DashboardVehicle> findByCustomer_Id(Long customerId);
}