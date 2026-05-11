package com.gms.backend.serviceadvisor.communication.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "communication")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobCardId;
    private Long customerId;

    private String type;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String subject;
    private String sentTo;

    private String status;

    private LocalDateTime createdAt;
    private String createdBy;
}