package com.gms.backend.jobcard.myjobs.service;

import com.gms.backend.jobcard.myjobs.dto.MyJobsDTO;
import com.gms.backend.jobcard.myjobs.dto.ServiceDTO;
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
    public List<MyJobsDTO> getAllJobs() {
        return repo.findAll().stream()
                .map(this::convertToMyJobsDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get Jobs By Status (for tabs)
    public List<ServiceDTO> getJobsByStatus(String status) {
        List<JobDetails> jobs = repo.findByStatus(status);

        return jobs.stream()
                .map(this::convertToServiceDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get Job By ID
    public MyJobsDTO getJobById(Long id) {
        JobDetails job = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        return convertToMyJobsDTO(job);
    }

    // ✅ Convert → MyJobsDTO
    private MyJobsDTO convertToMyJobsDTO(JobDetails job) {
        MyJobsDTO dto = new MyJobsDTO();

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
    private ServiceDTO convertToServiceDTO(JobDetails job) {
        ServiceDTO dto = new ServiceDTO();

        dto.setStatus(job.getStatus());
        dto.setDate(job.getDate());
        dto.setLocation(job.getLocation());
        dto.setAmount(job.getAmount());
        dto.setService(job.getTitle());

        return dto;
    }

    public MyJobsDTO updateJobStatus(Long id, String status) {
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