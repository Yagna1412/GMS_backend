package com.gms.backend.customer.bookservice.repo;

import com.gms.backend.customer.bookservice.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<ServiceEntity, Long> {
}