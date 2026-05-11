package com.gms.backend.customer.feedbacks.service;

import com.gms.backend.customer.feedbacks.dto.CreateTicketRequest;
import com.gms.backend.customer.feedbacks.dto.TicketResponseDTO;
import com.gms.backend.customer.feedbacks.dto.UpdateTicketRequest;

import java.util.List;

public interface SupportTicketService {

    // GET /api/tickets?customerId=1
    List<TicketResponseDTO> getTicketsByCustomer(Long customerId);

    // GET /api/tickets/{id}
    TicketResponseDTO getTicketById(Long id);

    // POST /api/tickets
    TicketResponseDTO createTicket(CreateTicketRequest request);

    // PUT /api/tickets/{id}
    TicketResponseDTO updateTicket(Long id, UpdateTicketRequest request);

    // DELETE /api/tickets/{id}
    void deleteTicket(Long id);
}