package com.gms.backend.hr.dashboard.dto;

import java.time.LocalDate;

public class GrievanceDTO {
    private Long id;
    private Long employeeId;
    private LocalDate grievanceDate;
    private String grievanceType;
    private String severity;
    private String description;
    private String status;
    private Long assignedTo;
    private LocalDate resolutionDate;
    private String resolutionNotes;

    public GrievanceDTO() {}

    public GrievanceDTO(Long employeeId, LocalDate grievanceDate, String grievanceType, String severity, String description) {
        this.employeeId = employeeId;
        this.grievanceDate = grievanceDate;
        this.grievanceType = grievanceType;
        this.severity = severity;
        this.description = description;
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
