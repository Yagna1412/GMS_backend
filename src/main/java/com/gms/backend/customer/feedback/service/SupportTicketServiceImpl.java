package com.gms.backend.customer.feedback.service;

import com.gms.backend.customer.feedback.dto.CreateTicketRequest;
import com.gms.backend.customer.feedback.dto.TicketResponseDTO;
import com.gms.backend.customer.feedback.dto.UpdateTicketRequest;
import com.gms.backend.customer.feedback.entity.SupportTicket;
import com.gms.backend.customer.feedback.repository.SupportTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupportTicketServiceImpl implements SupportTicketService {

    private final SupportTicketRepository ticketRepository;

    // ─────────────────────────────────────────────
    // GET /api/tickets?customerId=1
    // Returns all tickets for a customer
    // ─────────────────────────────────────────────
    @Override
    public List<TicketResponseDTO> getTicketsByCustomer(Long customerId) {
        return ticketRepository
                .findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ─────────────────────────────────────────────
    // GET /api/tickets/{id}
    // Returns single ticket with full details
    // ─────────────────────────────────────────────
    @Override
    public TicketResponseDTO getTicketById(Long id) {
        SupportTicket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));
        return mapToDTO(ticket);
    }

    // ─────────────────────────────────────────────
    // POST /api/tickets
    // Creates a new support ticket
    // ─────────────────────────────────────────────
    @Override
    public TicketResponseDTO createTicket(CreateTicketRequest request) {
        SupportTicket ticket = SupportTicket.builder()
                .customerId(request.getCustomerId())
                .issueType(request.getIssueType())
                .description(request.getDescription())
                .attachmentPath(request.getAttachmentPath())
                .status(SupportTicket.TicketStatus.Open) // always starts as Open
                .build();

        // Save first to get the auto-generated id
        SupportTicket saved = ticketRepository.save(ticket);

        // Now generate display ID like "#T1", "#T2" from the DB id
        saved.setTicketDisplayId("#T" + saved.getId());
        SupportTicket final_saved = ticketRepository.save(saved);

        return mapToDTO(final_saved);
    }

    // ─────────────────────────────────────────────
    // PUT /api/tickets/{id}
    // Updates status and/or all fields
    // ─────────────────────────────────────────────
    @Override
    public TicketResponseDTO updateTicket(Long id, UpdateTicketRequest request) {
        SupportTicket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + id));

        // Only update fields that are NOT null in the request
        if (request.getIssueType() != null) {
            ticket.setIssueType(request.getIssueType());
        }
        if (request.getDescription() != null) {
            ticket.setDescription(request.getDescription());
        }
        if (request.getAttachmentPath() != null) {
            ticket.setAttachmentPath(request.getAttachmentPath());
        }
        if (request.getStatus() != null) {
            ticket.setStatus(request.getStatus());
        }

        return mapToDTO(ticketRepository.save(ticket));
    }

    // ─────────────────────────────────────────────
    // DELETE /api/tickets/{id}
    // Hard deletes the ticket from DB permanently
    // ─────────────────────────────────────────────
    @Override
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new RuntimeException("Ticket not found with id: " + id);
        }
        ticketRepository.deleteById(id);
    }

    // ─────────────────────────────────────────────
    // Private mapper — entity to DTO
    // ─────────────────────────────────────────────
    private TicketResponseDTO mapToDTO(SupportTicket ticket) {
        return TicketResponseDTO.builder()
                .id(ticket.getId())
                .ticketDisplayId(ticket.getTicketDisplayId())
                .customerId(ticket.getCustomerId())
                .issueType(ticket.getIssueType())
                .description(ticket.getDescription())
                .attachmentPath(ticket.getAttachmentPath())
                .status(ticket.getStatus())
                .dateCreated(ticket.getDateCreated())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }
}