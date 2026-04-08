package com.gms.backend.jobcard.myjobs.service;

import com.gms.backend.jobcard.myjobs.dto.JobDetailsDTO;
import com.gms.backend.jobcard.myjobs.dto.JobsDTO;
import com.gms.backend.jobcard.myjobs.entity.JobDetails;
import com.gms.backend.jobcard.myjobs.repository.MyJobsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private MyJobsRepository repo;

    // 1. Get All Jobs
    public List<JobDetailsDTO> getAllJobs() {
        return repo.findAll().stream()
                .map(this::convertToJobDetailsDTO)
                .collect(Collectors.toList());
    }

    // 2. Get Jobs By Status — returns full DTO for frontend consistency
    public List<JobDetailsDTO> getJobsByStatus(String status) {
        return repo.findByStatus(status).stream()
                .map(this::convertToJobDetailsDTO)
                .collect(Collectors.toList());
    }

    // 3. Get Job Details by ID
    public JobDetailsDTO getJobById(Long id) {
        JobDetails job = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
        return convertToJobDetailsDTO(job);
    }

    // 4. Update Job Status
    public JobDetailsDTO updateJobStatus(Long id, String status) {
        JobDetails job = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
        job.setStatus(status);
        return convertToJobDetailsDTO(repo.save(job));
    }

    // 5. Delete Job
    public void deleteJob(Long id) {
        JobDetails job = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
        repo.delete(job);
    }

    // Converter
    private JobDetailsDTO convertToJobDetailsDTO(JobDetails job) {
        JobDetailsDTO dto = new JobDetailsDTO();
        dto.setId(job.getId());
        dto.setStatus(job.getStatus());
        dto.setLocation(job.getLocation());
        dto.setDate(job.getDate());
        dto.setCustomer(job.getCustomer());
        dto.setVehicle(job.getVehicle());
        dto.setCarNumber(job.getCarNumber());
        dto.setMechanic(job.getMechanic());
        dto.setAmount(job.getAmount());
        dto.setServices(job.getServices());
        return dto;
    }
}