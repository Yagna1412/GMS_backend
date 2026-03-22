package com.gms.backend.customer.feedback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "support_tickets")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupportTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ticket_display_id", unique = true)
    private String ticketDisplayId;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "issue_type", nullable = false)
    private String issueType;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "attachment_path")
    private String attachmentPath;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TicketStatus status;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.dateCreated == null) {
            this.dateCreated = LocalDate.now();
        }
        if (this.status == null) {
            this.status = TicketStatus.Open;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum TicketStatus {
        Open,
        In_Progress,
        Resolved,
        Closed
    }
}