package com.gms.backend.technician.progress.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "progress_updates")
public class ProgressUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobId;

    @Column(columnDefinition = "TEXT")
    private String note;

    private LocalDateTime createdAt;

    //  FIX: JSON infinite loop avoid
    @OneToMany(mappedBy = "progressUpdate", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProgressPhoto> photos;

    //  GETTERS & SETTERS (must)

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

    public List<ProgressPhoto> getPhotos() {
        return photos;
    }

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

    public void setPhotos(List<ProgressPhoto> photos) {
        this.photos = photos;
    }
}