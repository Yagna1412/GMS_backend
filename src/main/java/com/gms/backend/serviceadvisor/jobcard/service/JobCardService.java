package com.gms.backend.serviceadvisor.jobcard.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gms.backend.serviceadvisor.jobcard.dto.*;
import com.gms.backend.serviceadvisor.jobcard.entity.JobCardEntity;
import com.gms.backend.serviceadvisor.jobcard.repository.JobCardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JobCardService {

    @Autowired
    private JobCardRepository jobRepo;




    private String generateJobCardId() {

        Long lastId = jobRepo.findTopByOrderByIdDesc()
                .map(JobCardEntity::getId)
                .orElse(0L);

        long next = lastId + 1;

        String year = String.valueOf(java.time.LocalDateTime.now().getYear());

        return "JC/MUM/" + year + "/" + String.format("%04d", next);
    }

    private int technicianIndex = 0;

    private String assignTechnician() {

        List<String> technicians = Arrays.asList(
                "Ravi",
                "Kiran",
                "Suresh",
                "Mahesh"
        );

        String technician = technicians.get(technicianIndex);

        technicianIndex = (technicianIndex + 1) % technicians.size();

        return technician;
    }


    private final ObjectMapper mapper = new ObjectMapper();

    //  ENTITY → RESPONSE DTO
    private JobCardResponseDto map(JobCardEntity job) {



        return new JobCardResponseDto(
                job.getId(),
                job.getJobCardId(),
                job.getCustomerId(),
                job.getCustomerName(),
                job.getVehicle(),
                job.getCustomerComplaints(),
                job.getOdometerReading(),
                job.getPriority(),
                job.getTechnicianName(),
                job.getStatus(),
                job.getProgress(),
                job.getCreatedAt()
        );
    }







    // CREATE
    public JobCardResponseDto create(JobCardRequestDto dto) {

        List<String> valid = List.of("Normal","High","Urgent","VIP");

        if (!valid.contains(dto.getPriority())) {
            throw new RuntimeException("Invalid priority");
        }

        JobCardEntity job = new JobCardEntity();

        job.setJobCardId(generateJobCardId());
        job.setCustomerId(dto.getCustomerId());
        job.setCustomerName(dto.getCustomerName());


        job.setVehicle(dto.getVehicle());
        job.setCustomerComplaints(dto.getCustomerComplaints());
        job.setOdometerReading(dto.getOdometerReading());
        job.setPriority(dto.getPriority());
        job.setTechnicianName(dto.getTechnicianName());


        if (dto.getTechnicianName() == null ||
                dto.getTechnicianName().equalsIgnoreCase("Auto-assign")) {

            job.setTechnicianName(assignTechnician()); // random assign
        } else {
            job.setTechnicianName(dto.getTechnicianName());
        }

        job.setStatus("In-Progress");
        job.setProgress(0);

        return map(jobRepo.save(job));
    }

    // GET ALL
    public List<JobCardResponseDto> getAll() {
        return jobRepo.findAll().stream().map(this::map).toList();
    }

    // GET BY ID
    public JobCardResponseDto getById(Long id) {
        return map(jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found")));
    }

    // UPDATE
    public JobCardResponseDto update(Long id, JobCardRequestDto dto) {

        JobCardEntity job = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        List<String> valid = List.of("Normal","High","Urgent","VIP");

        if (!valid.contains(dto.getPriority())) {
            throw new RuntimeException("Invalid priority");
        }

        job.setCustomerId(dto.getCustomerId());
        job.setCustomerName(dto.getCustomerName());
        job.setVehicle(dto.getVehicle());
        job.setCustomerComplaints(dto.getCustomerComplaints());
        job.setOdometerReading(dto.getOdometerReading());
        job.setPriority(dto.getPriority());
        job.setTechnicianName(dto.getTechnicianName());

        return map(jobRepo.save(job));
    }

    // DELETE
    public void delete(Long id) {
        jobRepo.deleteById(id);
    }

    // STATUS UPDATE
    public JobCardResponseDto updateStatus(Long id, Map<String, Object> body) {

        JobCardEntity job = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (body.get("status") != null) {
            job.setStatus(body.get("status").toString());
        }

        if (body.get("progress") != null) {
            int progress = Integer.parseInt(body.get("progress").toString());
            job.setProgress(Math.max(0, Math.min(100, progress)));
        }

        return map(jobRepo.save(job));
    }


    public Map<String, Long> getDashboard() {

        List<JobCardEntity> list = jobRepo.findAll();

        Map<String, Long> data = new HashMap<>();

        //  Total Job Cards
        data.put("totalJobCards", (long) list.size());

        //  In Progress
        data.put("inProgress",
                list.stream()
                        .filter(j -> "In-Progress".equalsIgnoreCase(j.getStatus()))
                        .count());

        //  VIP Priority
        data.put("vipPriority",
                list.stream()
                        .filter(j -> "VIP".equalsIgnoreCase(j.getPriority()))
                        .count());

        //  Completed Today
        data.put("completedToday",
                list.stream()
                        .filter(j -> "Completed".equalsIgnoreCase(j.getStatus()))
                        .filter(j -> j.getCreatedAt() != null)
                        .filter(j -> j.getCreatedAt().toLocalDate()
                                .equals(java.time.LocalDate.now()))
                        .count());

        return data;
    }
}