package com.gms.backend.hr.dashboard.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "grievances")
public class Grievance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;
    
    @Column(name = "grievance_date", nullable = false)
    private LocalDate grievanceDate;
    
    @Column(name = "grievance_type", nullable = false) // HARASSMENT, DISCRIMINATION, WAGE_DISPUTE, SAFETY, OTHER
    private String grievanceType;
    
    @Column(name = "severity") // LOW, MEDIUM, HIGH
    private String severity;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "status") // OPEN, IN_PROGRESS, RESOLVED, CLOSED
    private String status;
    
    @Column(name = "assigned_to")
    private Long assignedTo;
    
    @Column(name = "resolution_date")
    private LocalDate resolutionDate;
    
    @Column(name = "resolution_notes", columnDefinition = "TEXT")
    private String resolutionNotes;

    public Grievance() {}

    public Grievance(Long employeeId, LocalDate grievanceDate, String grievanceType, String severity, String description) {
        this.employeeId = employeeId;
        this.grievanceDate = grievanceDate;
        this.grievanceType = grievanceType;
        this.severity = severity;
        this.description = description;
        this.status = "OPEN";
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public LocalDate getGrievanceDate() { return grievanceDate; }
    public void setGrievanceDate(LocalDate grievanceDate) { this.grievanceDate = grievanceDate; }

    public String getGrievanceType() { return grievanceType; }
    public void setGrievanceType(String grievanceType) { this.grievanceType = grievanceType; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Long assignedTo) { this.assignedTo = assignedTo; }

    public LocalDate getResolutionDate() { return resolutionDate; }
    public void setResolutionDate(LocalDate resolutionDate) { this.resolutionDate = resolutionDate; }

    public String getResolutionNotes() { return resolutionNotes; }
    public void setResolutionNotes(String resolutionNotes) { this.resolutionNotes = resolutionNotes; }
}
