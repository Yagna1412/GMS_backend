package com.gms.backend.serviceadvisor.communication.repository;


import com.gms.backend.serviceadvisor.communication.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}