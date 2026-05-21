package com.gms.backend.hr.controller;

import com.gms.backend.hr.dto.EmployeeDTO;
import com.gms.backend.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hr/employees")
@CrossOrigin("*")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO created = employeeService.createEmployee(employeeDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        try {
            EmployeeDTO updated = employeeService.updateEmployee(id, employeeDTO);
            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            }
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/department/{department}")
    public ResponseEntity<?> getEmployeesByDepartment(@PathVariable String department) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(department);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getEmployeesByStatus(@PathVariable String status) {
        List<EmployeeDTO> employees = employeeService.getEmployeesByStatus(status);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    @GetMapping("/count/total")
    public ResponseEntity<?> getTotalEmployees() {
        long count = employeeService.getTotalEmployees();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    
    @GetMapping("/count/active")
    public ResponseEntity<?> getActiveEmployees() {
        long count = employeeService.getActiveEmployees();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
