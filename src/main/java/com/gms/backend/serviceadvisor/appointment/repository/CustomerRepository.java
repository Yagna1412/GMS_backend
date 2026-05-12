package com.gms.backend.serviceadvisor.appointment.repository;

import com.gms.backend.serviceadvisor.appointment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}