package com.gms.backend.technician.partsrequest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parts_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartsRequest {

    public enum Status {
        PENDING, APPROVED, REJECTED, RECEIVED
    }

    public enum Type {
        ADDITIONAL, REQUIRED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String requestId;

    @NotBlank
    @Column(nullable = false)
    private String jobCardId;

    @NotBlank
    @Column(nullable = false)
    private String partName;

    @NotBlank
    @Column(nullable = false)
    private String reason;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Type type = Type.ADDITIONAL;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status = Status.PENDING;

    @Column(nullable = false, updatable = false)
    private LocalDateTime requestedAt;


}