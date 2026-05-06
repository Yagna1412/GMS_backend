package com.gms.backend.technician.progress.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProgressUpdateResponse {

    private Long id;
    private String jobId;
    private String note;
    private LocalDateTime createdAt;
    private List<String> photoUrls;

    // GETTERS

    public Long getId() {
        return id;
    }

    public String getJobId() {
        return jobId;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    //  SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
}