package com.gms.backend.serviceadvisor.customers.repository;

import com.gms.backend.serviceadvisor.customers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContainingIgnoreCase(String name);
    List<Customer> findByType(String type);
    List<Customer> findByNameContainingIgnoreCaseAndType(String name, String type);
}