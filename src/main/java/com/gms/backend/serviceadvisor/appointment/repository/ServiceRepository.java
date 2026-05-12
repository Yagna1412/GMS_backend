package com.gms.backend.serviceadvisor.appointment.repository;

import com.gms.backend.serviceadvisor.appointment.model.ServiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceItem, Long> {
}