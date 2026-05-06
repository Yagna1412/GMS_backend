package com.gms.backend.technician.progress.entity;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "progress_photos")
public class ProgressPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl;

    // ✅ FIX: Lazy loading + JSON loop avoid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "update_id")
    @JsonBackReference
    private ProgressUpdate progressUpdate;

    //  GETTERS

    public Long getId() {
        return id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public ProgressUpdate getProgressUpdate() {
        return progressUpdate;
    }

    //  SETTERS

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setProgressUpdate(ProgressUpdate progressUpdate) {
        this.progressUpdate = progressUpdate;
    }
}