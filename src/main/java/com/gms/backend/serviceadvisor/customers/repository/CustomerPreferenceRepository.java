package com.gms.backend.serviceadvisor.customers.repository;

import com.gms.backend.serviceadvisor.customers.entity.CustomerPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerPreferenceRepository extends JpaRepository<CustomerPreference, Long> {
    List<CustomerPreference> findByCustomerId(Long customerId);
}