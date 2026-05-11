package com.gms.backend.technician.qc.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "quality_check_submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QCChecklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_card_id")
    private Long jobCardId;

    @Column(name = "mechanic_id")
    private String mechanicId;

    @Column(name = "checklist_data", columnDefinition = "TEXT")
    private String checklistData;

    @Column(name = "mechanic_notes")
    private String mechanicNotes;

    private String status;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "organization_id")
    private Integer organizationId;

    @Column(name = "branch_id")
    private Integer branchId;

    @Column(name = "staff_id")
    private Integer staffId;
}