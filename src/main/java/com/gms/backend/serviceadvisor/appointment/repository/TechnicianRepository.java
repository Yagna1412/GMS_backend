package com.gms.backend.serviceadvisor.appointment.repository;

import com.gms.backend.serviceadvisor.appointment.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {
}