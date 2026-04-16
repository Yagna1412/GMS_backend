package com.gms.backend.BookingService.repo;

import com.gms.backend.BookingService.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<ServiceEntity, Long> {
}