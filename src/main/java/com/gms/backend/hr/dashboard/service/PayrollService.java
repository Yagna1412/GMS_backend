package com.gms.backend.hr.dashboard.service;

import com.gms.backend.hr.dashboard.dto.PayrollDTO;
import com.gms.backend.hr.dashboard.entity.Payroll;
import com.gms.backend.hr.dashboard.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PayrollService {
    
    @Autowired
    private PayrollRepository payrollRepository;
    
    public PayrollDTO createPayroll(PayrollDTO payrollDTO) {
        Payroll payroll = new Payroll();
        payroll.setEmployeeId(payrollDTO.getEmployeeId());
        payroll.setPayrollMonth(payrollDTO.getPayrollMonth());
        payroll.setBasicSalary(payrollDTO.getBasicSalary());
        payroll.setAllowances(payrollDTO.getAllowances() != null ? payrollDTO.getAllowances() : BigDecimal.ZERO);
        payroll.setDeductions(payrollDTO.getDeductions() != null ? payrollDTO.getDeductions() : BigDecimal.ZERO);
        
        // Calculate net salary
        BigDecimal netSalary = payroll.getBasicSalary()
                .add(payroll.getAllowances())
                .subtract(payroll.getDeductions());
        payroll.setNetSalary(netSalary);
        payroll.setStatus("PENDING");
        
        Payroll saved = payrollRepository.save(payroll);
        return convertToDTO(saved);
    }
    
    public PayrollDTO getPayrollById(Long id) {
        Optional<Payroll> payroll = payrollRepository.findById(id);
        return payroll.map(this::convertToDTO).orElse(null);
    }
    
    public List<PayrollDTO> getEmployeePayroll(Long employeeId) {
        return payrollRepository.findByEmployeeId(employeeId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public PayrollDTO processPayroll(Long id) {
        Optional<Payroll> payroll = payrollRepository.findById(id);
        if (payroll.isPresent()) {
            Payroll p = payroll.get();
            p.setStatus("PROCESSED");
            p.setProcessedDate(LocalDate.now());
            
            Payroll updated = payrollRepository.save(p);
            return convertToDTO(updated);
        }
        return null;
    }
    
    public List<PayrollDTO> getPendingPayrolls() {
        return payrollRepository.findByStatus("PENDING").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public void deletePayroll(Long id) {
        payrollRepository.deleteById(id);
    }
    
    public BigDecimal getTotalPayrollForMonth(LocalDate month) {
        return payrollRepository.findByPayrollMonth(month).stream()
                .map(Payroll::getNetSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    private PayrollDTO convertToDTO(Payroll payroll) {
        PayrollDTO dto = new PayrollDTO();
        dto.setId(payroll.getId());
        dto.setEmployeeId(payroll.getEmployeeId());
        dto.setPayrollMonth(payroll.getPayrollMonth());
        dto.setBasicSalary(payroll.getBasicSalary());
        dto.setAllowances(payroll.getAllowances());
        dto.setDeductions(payroll.getDeductions());
        dto.setNetSalary(payroll.getNetSalary());
        dto.setStatus(payroll.getStatus());
        dto.setProcessedDate(payroll.getProcessedDate());
        return dto;
    }
}
