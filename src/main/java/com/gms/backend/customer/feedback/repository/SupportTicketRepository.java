package com.gms.backend.customer.feedback.repository;

import com.gms.backend.customer.feedback.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, Long> {

    // Get all tickets for a customer — newest first
    List<SupportTicket> findByCustomerIdOrderByCreatedAtDesc(Long customerId);

    // Find by display ID like "#T1"
    Optional<SupportTicket> findByTicketDisplayId(String ticketDisplayId);
}