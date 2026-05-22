package com.gms.backend.hr.employeemaster.service;

import com.gms.backend.hr.employeemaster.dto.EmployeeMasterRequestDto;
import com.gms.backend.hr.employeemaster.dto.EmployeeMasterResponseDto;

import java.util.List;

public interface EmployeeMasterService {

    String createEmployee(EmployeeMasterRequestDto employeeMasterRequestDto);

    List<EmployeeMasterResponseDto> getAllEmployees();

    EmployeeMasterResponseDto getEmployeeById(Long id);

    String updateEmployee(Long id,
                          EmployeeMasterRequestDto employeeMasterRequestDto);

    String deleteEmployee(Long id);
}
