package com.gms.backend.hr.service;

import com.gms.backend.hr.dto.GrievanceDTO;
import com.gms.backend.hr.entity.Grievance;
import com.gms.backend.hr.repository.GrievanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GrievanceService {
    
    @Autowired
    private GrievanceRepository grievanceRepository;
    
    public GrievanceDTO createGrievance(GrievanceDTO grievanceDTO) {
        Grievance grievance = new Grievance();
        grievance.setEmployeeId(grievanceDTO.getEmployeeId());
        grievance.setGrievanceDate(grievanceDTO.getGrievanceDate());
        grievance.setGrievanceType(grievanceDTO.getGrievanceType());
        grievance.setSeverity(grievanceDTO.getSeverity());
        grievance.setDescription(grievanceDTO.getDescription());
        grievance.setStatus("OPEN");
        
        Grievance saved = grievanceRepository.save(grievance);
        return convertToDTO(saved);
    }
    
    public GrievanceDTO getGrievanceById(Long id) {
        Optional<Grievance> grievance = grievanceRepository.findById(id);
        return grievance.map(this::convertToDTO).orElse(null);
    }
    
    public List<GrievanceDTO> getEmployeeGrievances(Long employeeId) {
        return grievanceRepository.findByEmployeeId(employeeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<GrievanceDTO> getOpenGrievances() {
        return grievanceRepository.findByStatus("OPEN").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<GrievanceDTO> getHighPriorityGrievances() {
        return grievanceRepository.findByStatusAndSeverity("OPEN", "HIGH").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public GrievanceDTO assignGrievance(Long id, Long assignedTo) {
        Optional<Grievance> grievance = grievanceRepository.findById(id);
        if (grievance.isPresent()) {
            Grievance g = grievance.get();
            g.setAssignedTo(assignedTo);
            g.setStatus("IN_PROGRESS");
            
            Grievance updated = grievanceRepository.save(g);
            return convertToDTO(updated);
        }
        return null;
    }
    
    public GrievanceDTO resolveGrievance(Long id, String resolutionNotes) {
        Optional<Grievance> grievance = grievanceRepository.findById(id);
        if (grievance.isPresent()) {
            Grievance g = grievance.get();
            g.setStatus("RESOLVED");
            g.setResolutionDate(LocalDate.now());
            g.setResolutionNotes(resolutionNotes);
            
            Grievance updated = grievanceRepository.save(g);
            return convertToDTO(updated);
        }
        return null;
    }
    
    public void deleteGrievance(Long id) {
        grievanceRepository.deleteById(id);
    }
    
    public long getHighSeverityGrievancesCount() {
        return grievanceRepository.findBySeverity("HIGH").size();
    }
    
    public long getOpenGrievancesCount() {
        return grievanceRepository.findByStatus("OPEN").size();
    }
    
    private GrievanceDTO convertToDTO(Grievance grievance) {
        GrievanceDTO dto = new GrievanceDTO();
        dto.setId(grievance.getId());
        dto.setEmployeeId(grievance.getEmployeeId());
        dto.setGrievanceDate(grievance.getGrievanceDate());
        dto.setGrievanceType(grievance.getGrievanceType());
        dto.setSeverity(grievance.getSeverity());
        dto.setDescription(grievance.getDescription());
        dto.setStatus(grievance.getStatus());
        dto.setAssignedTo(grievance.getAssignedTo());
        dto.setResolutionDate(grievance.getResolutionDate());
        dto.setResolutionNotes(grievance.getResolutionNotes());
        return dto;
    }
}
