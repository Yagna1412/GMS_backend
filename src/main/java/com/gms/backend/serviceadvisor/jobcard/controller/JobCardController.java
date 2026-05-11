package com.gms.backend.serviceadvisor.jobcard.controller;

import com.gms.backend.serviceadvisor.jobcard.dto.*;
import com.gms.backend.serviceadvisor.jobcard.service.JobCardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/job-cards")
@CrossOrigin(origins = "http://localhost:5173")
public class JobCardController {

    @Autowired
    private JobCardService service;

    @PostMapping
    public JobCardResponseDto create(@RequestBody JobCardRequestDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<JobCardResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public JobCardResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public JobCardResponseDto update(@PathVariable Long id,
                                     @RequestBody JobCardRequestDto dto) {
        return service.update(id, dto);
    }

    @PutMapping("/{id}/status")
    public JobCardResponseDto updateStatus(@PathVariable Long id,
                                           @RequestBody Map<String, Object> body) {
        return service.updateStatus(id, body);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted Successfully";
    }


    @GetMapping("/dashboard")
    public Map<String, Long> dashboard() {
        return service.getDashboard();
    }
}