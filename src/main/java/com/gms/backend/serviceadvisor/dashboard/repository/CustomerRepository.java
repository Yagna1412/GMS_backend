package com.gms.backend.serviceadvisor.dashboard.repository;

import com.gms.backend.serviceadvisor.dashboard.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}