package com.gms.backend.hr.controller;

import com.gms.backend.hr.dto.GrievanceDTO;
import com.gms.backend.hr.service.GrievanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hr/grievances")
@CrossOrigin("*")
public class GrievanceController {
    
    @Autowired
    private GrievanceService grievanceService;
    
    @PostMapping
    public ResponseEntity<?> createGrievance(@RequestBody GrievanceDTO grievanceDTO) {
        try {
            GrievanceDTO created = grievanceService.createGrievance(grievanceDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getGrievance(@PathVariable Long id) {
        GrievanceDTO grievance = grievanceService.getGrievanceById(id);
        if (grievance != null) {
            return new ResponseEntity<>(grievance, HttpStatus.OK);
        }
        return new ResponseEntity<>("Grievance not found", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getEmployeeGrievances(@PathVariable Long employeeId) {
        List<GrievanceDTO> grievances = grievanceService.getEmployeeGrievances(employeeId);
        return new ResponseEntity<>(grievances, HttpStatus.OK);
    }
    
    @GetMapping("/open")
    public ResponseEntity<?> getOpenGrievances() {
        List<GrievanceDTO> grievances = grievanceService.getOpenGrievances();
        return new ResponseEntity<>(grievances, HttpStatus.OK);
    }
    
    @GetMapping("/high-priority")
    public ResponseEntity<?> getHighPriorityGrievances() {
        List<GrievanceDTO> grievances = grievanceService.getHighPriorityGrievances();
        return new ResponseEntity<>(grievances, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/assign")
    public ResponseEntity<?> assignGrievance(@PathVariable Long id, @RequestParam Long assignedTo) {
        try {
            GrievanceDTO assigned = grievanceService.assignGrievance(id, assignedTo);
            if (assigned != null) {
                return new ResponseEntity<>(assigned, HttpStatus.OK);
            }
            return new ResponseEntity<>("Grievance not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{id}/resolve")
    public ResponseEntity<?> resolveGrievance(@PathVariable Long id, @RequestParam String resolutionNotes) {
        try {
            GrievanceDTO resolved = grievanceService.resolveGrievance(id, resolutionNotes);
            if (resolved != null) {
                return new ResponseEntity<>(resolved, HttpStatus.OK);
            }
            return new ResponseEntity<>("Grievance not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGrievance(@PathVariable Long id) {
        try {
            grievanceService.deleteGrievance(id);
            return new ResponseEntity<>("Grievance deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/count/high-severity")
    public ResponseEntity<?> getHighSeverityGrievancesCount() {
        long count = grievanceService.getHighSeverityGrievancesCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    
    @GetMapping("/count/open")
    public ResponseEntity<?> getOpenGrievancesCount() {
        long count = grievanceService.getOpenGrievancesCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
