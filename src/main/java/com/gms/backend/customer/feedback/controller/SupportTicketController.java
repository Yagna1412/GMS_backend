package com.gms.backend.customer.feedback.controller;

import com.gms.backend.customer.feedback.dto.CreateTicketRequest;
import com.gms.backend.customer.feedback.dto.TicketResponseDTO;
import com.gms.backend.customer.feedback.dto.UpdateTicketRequest;
import com.gms.backend.customer.feedback.service.SupportTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class SupportTicketController {

    private final SupportTicketService ticketService;

    // ─────────────────────────────────────────────
    // GET /api/tickets?customerId=1
    // Support Tickets tab — shows all tickets list
    // ─────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getTickets(
            @RequestParam Long customerId
    ) {
        return ResponseEntity.ok(ticketService.getTicketsByCustomer(customerId));
    }

    // ─────────────────────────────────────────────
    // GET /api/tickets/{id}
    // Single ticket detail with full description + attachment
    // ─────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }

    // ─────────────────────────────────────────────
    // POST /api/tickets
    // Create new ticket — "Open Ticket" button in modal
    // ─────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(
            @RequestBody CreateTicketRequest request
    ) {
        TicketResponseDTO created = ticketService.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ─────────────────────────────────────────────
    // PUT /api/tickets/{id}
    // Update ticket — edit icon (pencil) in table
    // ─────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(
            @PathVariable Long id,
            @RequestBody UpdateTicketRequest request
    ) {
        return ResponseEntity.ok(ticketService.updateTicket(id, request));
    }

    // ─────────────────────────────────────────────
    // DELETE /api/tickets/{id}
    // Hard delete — red trash icon in table
    // ─────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build(); // returns 204 No Content
    }
}