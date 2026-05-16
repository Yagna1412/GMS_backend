package com.gms.backend.serviceadvisor.customers.repository;

import com.gms.backend.serviceadvisor.customers.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByCustomerId(Long customerId);
}