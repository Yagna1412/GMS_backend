package com.gms.backend.hr.dashboard.dto;

import java.time.LocalDate;

public class TrainingProgramDTO {
    private Long id;
    private String programName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String trainer;
    private String location;
    private String description;
    private String status;
    private Integer capacity;

    public TrainingProgramDTO() {}

    public TrainingProgramDTO(String programName, LocalDate startDate, LocalDate endDate) {
        this.programName = programName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProgramName() { return programName; }
    public void setProgramName(String programName) { this.programName = programName; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getTrainer() { return trainer; }
    public void setTrainer(String trainer) { this.trainer = trainer; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}
