package com.gms.backend.serviceadvisor.tracking.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_tracking")
public class JobTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobCardId;

    private String status;

    private String remarks;

    private int progress;

    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getJobCardId() {
        return jobCardId;
    }

    public void setJobCardId(Long jobCardId) {
        this.jobCardId = jobCardId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}