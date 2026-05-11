package com.gms.backend.serviceadvisor.tracking.service;

import com.gms.backend.serviceadvisor.tracking.dto.JobTrackingDto;
import com.gms.backend.serviceadvisor.tracking.entity.JobTracking;
import com.gms.backend.serviceadvisor.tracking.repository.JobTrackingRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobTrackingService {

    private final JobTrackingRepository repository;

    public JobTrackingService(JobTrackingRepository repository) {
        this.repository = repository;
    }

    // =========================
    // CREATE
    // =========================
    public JobTracking createTracking(JobTrackingDto dto) {

        JobTracking entity = new JobTracking();

        entity.setJobCardId(dto.getJobCardId());
        entity.setStatus(dto.getStatus());
        entity.setRemarks(dto.getRemarks());
        entity.setProgress(dto.getProgress());

        return repository.save(entity);
    }

    // =========================
    // GET TRACKING HISTORY
    // =========================
    public List<JobTracking> getTrackingByJob(Long jobCardId) {
        return repository.findByJobCardId(jobCardId);
    }

    // =========================
    // GET ALL JOBS
    // =========================
    public List<JobTrackingDto> getAllJobStatuses() {

        List<JobTracking> all = repository.findAll();

        Map<Long, JobTracking> latestMap = new HashMap<>();

        for (JobTracking job : all) {

            Long jobCardId = job.getJobCardId();

            if (!latestMap.containsKey(jobCardId)) {

                latestMap.put(jobCardId, job);

            } else {

                JobTracking existing = latestMap.get(jobCardId);

                if (job.getUpdatedAt() != null &&
                        (existing.getUpdatedAt() == null ||
                                job.getUpdatedAt().isAfter(existing.getUpdatedAt()))) {

                    latestMap.put(jobCardId, job);
                }
            }
        }

        return latestMap.values()
                .stream()
                .map(j -> {

                    JobTrackingDto dto = new JobTrackingDto();

                    dto.setJobCardId(j.getJobCardId());
                    dto.setStatus(j.getStatus());
                    dto.setRemarks(j.getRemarks());
                    dto.setProgress(j.getProgress());

                    dto.setCustomerName("Customer " + j.getJobCardId());
                    dto.setTechnicianName("Technician " + j.getJobCardId());

                    return dto;
                })
                .collect(Collectors.toList());
    }

    // =========================
    // SUMMARY
    // =========================
    public Map<String, Long> getSummaryCounts() {

        List<JobTracking> all = repository.findAll();

        Map<String, Long> map = new HashMap<>();

        map.put("Active Jobs", (long) all.size());

        map.put(
                "In Progress",
                all.stream()
                        .filter(j -> normalize(j.getStatus()).contains("progress"))
                        .count()
        );

        map.put(
                "Quality Check",
                all.stream()
                        .filter(j -> normalize(j.getStatus()).contains("quality"))
                        .count()
        );

        return map;
    }

    // =========================
    // UPDATE
    // =========================
    public JobTracking updateTracking(Long jobCardId, JobTrackingDto dto) {

        JobTracking entity = repository
                .findTopByJobCardIdOrderByUpdatedAtDesc(jobCardId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        entity.setStatus(dto.getStatus());
        entity.setRemarks(dto.getRemarks());
        entity.setProgress(dto.getProgress());

        return repository.save(entity);
    }

    // =========================
    // DELETE
    // =========================
    @Transactional
    public void deleteByJobCardId(Long jobCardId) {

        Optional<JobTracking> existing =
                repository.findTopByJobCardIdOrderByUpdatedAtDesc(jobCardId);

        if (existing.isEmpty()) {
            throw new RuntimeException("Job not found");
        }

        repository.deleteByJobCardId(jobCardId);
    }

    // =========================
    // HELPER
    // =========================
    private String normalize(String status) {
        return status == null ? "" : status.toLowerCase();
    }
}