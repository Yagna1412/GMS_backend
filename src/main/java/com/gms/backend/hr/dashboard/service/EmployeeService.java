package com.gms.backend.hr.dashboard.service;

import com.gms.backend.hr.dashboard.dto.EmployeeDTO;
import com.gms.backend.hr.dashboard.entity.Employee;
import com.gms.backend.hr.dashboard.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setDepartment(employeeDTO.getDepartment());
        employee.setPosition(employeeDTO.getPosition());
        employee.setJoiningDate(employeeDTO.getJoiningDate());
        employee.setStatus(employeeDTO.getStatus() != null ? employeeDTO.getStatus() : "ACTIVE");
        
        Employee saved = employeeRepository.save(employee);
        return convertToDTO(saved);
    }
    
    public EmployeeDTO getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(this::convertToDTO).orElse(null);
    }
    
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
            emp.setFirstName(employeeDTO.getFirstName());
            emp.setLastName(employeeDTO.getLastName());
            emp.setPhone(employeeDTO.getPhone());
            emp.setDepartment(employeeDTO.getDepartment());
            emp.setPosition(employeeDTO.getPosition());
            emp.setStatus(employeeDTO.getStatus());
            emp.setManagerId(employeeDTO.getManagerId());
            
            Employee updated = employeeRepository.save(emp);
            return convertToDTO(updated);
        }
        return null;
    }
    
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartment(department).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<EmployeeDTO> getEmployeesByStatus(String status) {
        return employeeRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public long getTotalEmployees() {
        return employeeRepository.count();
    }
    
    public long getActiveEmployees() {
        return employeeRepository.findByStatus("ACTIVE").size();
    }
    
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDepartment(employee.getDepartment());
        dto.setPosition(employee.getPosition());
        dto.setJoiningDate(employee.getJoiningDate());
        dto.setStatus(employee.getStatus());
        dto.setManagerId(employee.getManagerId());
        return dto;
    }
}
