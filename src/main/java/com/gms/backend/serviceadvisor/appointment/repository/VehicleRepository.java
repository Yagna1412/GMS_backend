package com.gms.backend.serviceadvisor.appointment.repository;

import com.gms.backend.serviceadvisor.appointment.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByCustomerId(Long customerId);
}