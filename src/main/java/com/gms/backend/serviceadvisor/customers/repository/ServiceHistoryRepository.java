package com.gms.backend.serviceadvisor.customers.repository;

import com.gms.backend.serviceadvisor.customers.entity.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceHistoryRepository extends JpaRepository<ServiceHistory, Long> {
    List<ServiceHistory> findByCustomerId(Long customerId);
}