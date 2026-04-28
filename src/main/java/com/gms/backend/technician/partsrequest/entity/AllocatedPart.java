package com.gms.backend.technician.partsrequest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@Entity
@Table(name = "allocated_parts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocatedPart {

    public enum Status {
//        PENDING,
        ISSUED, RECEIVED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String jobCardId;

    @NotBlank
    @Column(nullable = false)
    private String partName;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Status status = Status.ISSUED;






}