package com.gms.backend.serviceadvisor.tracking.service;

import com.gms.backend.serviceadvisor.tracking.dto.JobTrackingDto;
import com.gms.backend.serviceadvisor.tracking.entity.JobTracking;
import com.gms.backend.serviceadvisor.tracking.repository.JobTrackingRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobTrackingService {

    private final JobTrackingRepository repository;

    public JobTrackingService(JobTrackingRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public JobTracking createTracking(JobTrackingDto dto) {
        JobTracking entity = new JobTracking();

        entity.setJobCardId(dto.getJobCardId());
        entity.setStatus(dto.getStatus());
        entity.setRemarks(dto.getRemarks());
        entity.setProgress(dto.getProgress());
        entity.setUpdatedAt(LocalDateTime.now());

        return repository.save(entity);
    }

    // HISTORY
    public List<JobTracking> getTrackingByJob(Long jobCardId) {
        return repository.findByJobCardId(jobCardId);
    }

    // TABLE DATA
    public List<JobTrackingDto> getAllJobStatuses() {
        List<JobTracking> list = repository.findAll();

        return list.stream().map(j -> {
            JobTrackingDto dto = new JobTrackingDto();
            dto.setJobCardId(j.getJobCardId());
            dto.setStatus(j.getStatus());
            dto.setProgress(j.getProgress());

            dto.setCustomerName("Customer " + j.getJobCardId());
            dto.setTechnicianName("Technician " + j.getJobCardId());

            return dto;
        }).collect(Collectors.toList());
    }

    // SUMMARY
    public Map<String, Long> getSummaryCounts() {
        Map<String, Long> map = new HashMap<>();

        map.put("Active Jobs", repository.count());
        map.put("In Progress", repository.findByStatus("In-Progress").stream().count());
        map.put("Quality Check", repository.findByStatus("Quality Check").stream().count());

        return map;
    }

    // ✅ FIXED UPDATE (IMPORTANT)
    public JobTracking updateTracking(Long jobCardId, JobTrackingDto dto) {

        // latest record fetch
        JobTracking entity = repository.findByJobCardId(jobCardId)
                .stream()
                .reduce((first, second) -> second)
                .orElse(new JobTracking());

        entity.setJobCardId(jobCardId);
        entity.setStatus(dto.getStatus());
        entity.setRemarks(dto.getRemarks());
        entity.setProgress(dto.getProgress());
        entity.setUpdatedAt(LocalDateTime.now());

        return repository.save(entity);
    }

    // DELETE
    public void deleteTracking(Long jobCardId) {
        List<JobTracking> list = repository.findByJobCardId(jobCardId);
        repository.deleteAll(list);
    }
}