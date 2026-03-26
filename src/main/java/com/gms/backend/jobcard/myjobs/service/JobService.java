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

    // ✅ Get All Jobs
    public List<JobDetailsDTO> getAllJobs() {
        return repo.findAll().stream()
                .map(this::convertToMyJobsDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get Jobs By Status (for tabs)
    public List<JobsDTO> getJobsByStatus(String status) {
        List<JobDetails> jobs = repo.findByStatus(status);

        return jobs.stream()
                .map(this::convertToServiceDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get Job By ID
    public JobDetailsDTO getJobById(Long id) {
        JobDetails job = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        return convertToMyJobsDTO(job);
    }

    // ✅ Convert → MyJobsDTO
    private JobDetailsDTO convertToMyJobsDTO(JobDetails job) {
        JobDetailsDTO dto = new JobDetailsDTO();

        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setStatus(job.getStatus());
        dto.setLocation(job.getLocation());
        dto.setDate(job.getDate());
        dto.setCustomer(job.getCustomer());
        dto.setVehicle(job.getVehicle());
        dto.setCarNumber(job.getCarNumber());
        dto.setMechanic(job.getMechanic());
        dto.setAmount(job.getAmount());
        dto.setServices(job.getTitle());

        return dto;
    }

    // ✅ Convert → ServiceDTO (for tabs)
    private JobsDTO convertToServiceDTO(JobDetails job) {
        JobsDTO dto = new JobsDTO();

        dto.setStatus(job.getStatus());
        dto.setDate(job.getDate());
        dto.setLocation(job.getLocation());
        dto.setAmount(job.getAmount());
        dto.setService(job.getTitle());

        return dto;
    }

    public JobDetailsDTO updateJobStatus(Long id, String status) {
        JobDetails job = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        job.setStatus(status);
        JobDetails updatedJob = repo.save(job);

        return convertToMyJobsDTO(updatedJob);
    }

    public void deleteJob(Long id) {

        JobDetails job = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        repo.delete(job);
    }
}