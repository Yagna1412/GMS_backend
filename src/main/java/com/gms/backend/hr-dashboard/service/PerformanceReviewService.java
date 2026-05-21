package com.gms.backend.hr.service;

import com.gms.backend.hr.dto.PerformanceReviewDTO;
import com.gms.backend.hr.entity.PerformanceReview;
import com.gms.backend.hr.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerformanceReviewService {
    
    @Autowired
    private PerformanceReviewRepository performanceReviewRepository;
    
    public PerformanceReviewDTO createPerformanceReview(PerformanceReviewDTO performanceReviewDTO) {
        PerformanceReview review = new PerformanceReview();
        review.setEmployeeId(performanceReviewDTO.getEmployeeId());
        review.setReviewerId(performanceReviewDTO.getReviewerId());
        review.setReviewDate(performanceReviewDTO.getReviewDate());
        review.setStatus("PENDING");
        
        PerformanceReview saved = performanceReviewRepository.save(review);
        return convertToDTO(saved);
    }
    
    public PerformanceReviewDTO getPerformanceReviewById(Long id) {
        Optional<PerformanceReview> review = performanceReviewRepository.findById(id);
        return review.map(this::convertToDTO).orElse(null);
    }
    
    public List<PerformanceReviewDTO> getEmployeePerformanceReviews(Long employeeId) {
        return performanceReviewRepository.findByEmployeeId(employeeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<PerformanceReviewDTO> getReviewerPendingReviews(Long reviewerId) {
        return performanceReviewRepository.findByReviewerIdAndStatus(reviewerId, "PENDING").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public PerformanceReviewDTO submitPerformanceReview(Long id, PerformanceReviewDTO reviewDTO) {
        Optional<PerformanceReview> review = performanceReviewRepository.findById(id);
        if (review.isPresent()) {
            PerformanceReview pr = review.get();
            pr.setRating(reviewDTO.getRating());
            pr.setFeedback(reviewDTO.getFeedback());
            pr.setStatus("COMPLETED");
            
            PerformanceReview updated = performanceReviewRepository.save(pr);
            return convertToDTO(updated);
        }
        return null;
    }
    
    public void deletePerformanceReview(Long id) {
        performanceReviewRepository.deleteById(id);
    }
    
    public long getPendingReviewCount() {
        return performanceReviewRepository.findByStatus("PENDING").size();
    }
    
    private PerformanceReviewDTO convertToDTO(PerformanceReview review) {
        PerformanceReviewDTO dto = new PerformanceReviewDTO();
        dto.setId(review.getId());
        dto.setEmployeeId(review.getEmployeeId());
        dto.setReviewerId(review.getReviewerId());
        dto.setReviewDate(review.getReviewDate());
        dto.setRating(review.getRating());
        dto.setFeedback(review.getFeedback());
        dto.setStatus(review.getStatus());
        dto.setReviewPeriod(review.getReviewPeriod());
        return dto;
    }
}
