package com.gms.backend.hr.dto;

import java.time.LocalDate;

public class PerformanceReviewDTO {
    private Long id;
    private Long employeeId;
    private Long reviewerId;
    private LocalDate reviewDate;
    private Integer rating;
    private String feedback;
    private String status;
    private String reviewPeriod;

    public PerformanceReviewDTO() {}

    public PerformanceReviewDTO(Long employeeId, Long reviewerId, LocalDate reviewDate) {
        this.employeeId = employeeId;
        this.reviewerId = reviewerId;
        this.reviewDate = reviewDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public Long getReviewerId() { return reviewerId; }
    public void setReviewerId(Long reviewerId) { this.reviewerId = reviewerId; }

    public LocalDate getReviewDate() { return reviewDate; }
    public void setReviewDate(LocalDate reviewDate) { this.reviewDate = reviewDate; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReviewPeriod() { return reviewPeriod; }
    public void setReviewPeriod(String reviewPeriod) { this.reviewPeriod = reviewPeriod; }
}
