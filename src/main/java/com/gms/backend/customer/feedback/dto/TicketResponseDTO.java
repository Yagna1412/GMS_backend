package com.gms.backend.customer.feedback.dto;

import com.gms.backend.customer.feedback.entity.SupportTicket.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDTO {

    private Long id;
    private String ticketDisplayId;  // e.g. "#T1" shown in TICKET ID column
    private Long customerId;
    private String issueType;        // shown in ISSUE column
    private String description;      // shown in detail view (GET by id)
    private String attachmentPath;   // shown in detail view (GET by id)
    private TicketStatus status;     // shown as badge: Open / Resolved etc.
    private LocalDate dateCreated;   // shown in DATE column
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}