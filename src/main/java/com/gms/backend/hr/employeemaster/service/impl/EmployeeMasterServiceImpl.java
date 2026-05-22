package com.gms.backend.hr.employeemaster.service.impl;

import com.gms.backend.hr.employeemaster.dto.EmployeeMasterRequestDto;
import com.gms.backend.hr.employeemaster.dto.EmployeeMasterResponseDto;
import com.gms.backend.hr.employeemaster.entity.EmployeeMasterEntity;
import com.gms.backend.hr.employeemaster.repository.EmployeeMasterRepository;
import com.gms.backend.hr.employeemaster.service.EmployeeMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeMasterServiceImpl
        implements EmployeeMasterService {

    @Autowired
    private EmployeeMasterRepository employeeMasterRepository;

    @Override
    public String createEmployee(
            EmployeeMasterRequestDto employeeMasterRequestDto) {

        EmployeeMasterEntity employeeMasterEntity =
                new EmployeeMasterEntity();

        employeeMasterEntity.setFullName(
                employeeMasterRequestDto.getFullName());

        employeeMasterEntity.setEmployeeCode(
                employeeMasterRequestDto.getEmployeeCode());

        employeeMasterEntity.setFirstName(
                employeeMasterRequestDto.getFirstName());

        employeeMasterEntity.setLastName(
                employeeMasterRequestDto.getLastName());

        employeeMasterEntity.setShiftName(
                employeeMasterRequestDto.getShiftName());
        employeeMasterEntity.setDesignation(
                employeeMasterRequestDto.getDesignation());

        employeeMasterEntity.setDepartment(
                employeeMasterRequestDto.getDepartment());
        employeeMasterEntity.setEmail(
                employeeMasterRequestDto.getEmail());

        employeeMasterEntity.setPhoneNumber(
                employeeMasterRequestDto.getPhoneNumber());

        employeeMasterEntity.setEmergencyContact(
                employeeMasterRequestDto.getEmergencyContact());

        employeeMasterEntity.setAddress(
                employeeMasterRequestDto.getAddress());

        employeeMasterEntity.setDesignationId(
                employeeMasterRequestDto.getDesignationId());

        employeeMasterEntity.setDepartmentId(
                employeeMasterRequestDto.getDepartmentId());

        employeeMasterEntity.setBranchId(
                employeeMasterRequestDto.getBranchId());

        employeeMasterEntity.setReportingManagerId(
                employeeMasterRequestDto.getReportingManagerId());

        employeeMasterEntity.setJoiningDate(
                employeeMasterRequestDto.getJoiningDate());

        employeeMasterEntity.setMonthlySalary(
                employeeMasterRequestDto.getMonthlySalary());

        employeeMasterEntity.setStatus(
                employeeMasterRequestDto.getStatus());

        employeeMasterRepository.save(employeeMasterEntity);

        return "Employee Created Successfully";
    }

    @Override
    public List<EmployeeMasterResponseDto> getAllEmployees() {

        List<EmployeeMasterEntity> employeeMasterEntities =
                employeeMasterRepository.findAll();

        List<EmployeeMasterResponseDto> responseDtos =
                new ArrayList<>();

        for (EmployeeMasterEntity employeeMasterEntity
                : employeeMasterEntities) {

            EmployeeMasterResponseDto responseDto =
                    new EmployeeMasterResponseDto();

            responseDto.setId(employeeMasterEntity.getId());

            responseDto.setEmployeeCode(
                    employeeMasterEntity.getEmployeeCode());

            responseDto.setFullName(
                    employeeMasterEntity.getFullName());

            responseDto.setFirstName(
                    employeeMasterEntity.getFirstName());

            responseDto.setLastName(
                    employeeMasterEntity.getLastName());

            responseDto.setShiftName(
                    employeeMasterEntity.getShiftName());
            responseDto.setDesignation(
                    employeeMasterEntity.getDesignation());

            responseDto.setDepartment(
                    employeeMasterEntity.getDepartment());
            responseDto.setEmail(
                    employeeMasterEntity.getEmail());

            responseDto.setPhoneNumber(
                    employeeMasterEntity.getPhoneNumber());

            responseDto.setStatus(
                    employeeMasterEntity.getStatus());

            responseDtos.add(responseDto);
        }

        return responseDtos;
    }

    @Override
    public EmployeeMasterResponseDto getEmployeeById(Long id) {

        Optional<EmployeeMasterEntity> optionalEmployee =
                employeeMasterRepository.findById(id);

        EmployeeMasterEntity employeeMasterEntity =
                optionalEmployee.get();

        EmployeeMasterResponseDto responseDto =
                new EmployeeMasterResponseDto();

        responseDto.setId(employeeMasterEntity.getId());

        responseDto.setEmployeeCode(
                employeeMasterEntity.getEmployeeCode());

        responseDto.setFullName(
                employeeMasterEntity.getFullName());

        responseDto.setFirstName(
                employeeMasterEntity.getFirstName());

        responseDto.setLastName(
                employeeMasterEntity.getLastName());

        responseDto.setShiftName(
                employeeMasterEntity.getShiftName());
        responseDto.setDesignation(
                employeeMasterEntity.getDesignation());

        responseDto.setDepartment(
                employeeMasterEntity.getDepartment());
        responseDto.setEmail(
                employeeMasterEntity.getEmail());

        responseDto.setPhoneNumber(
                employeeMasterEntity.getPhoneNumber());

        responseDto.setStatus(
                employeeMasterEntity.getStatus());

        return responseDto;
    }

    @Override
    public String updateEmployee(Long id,
                                 EmployeeMasterRequestDto employeeMasterRequestDto) {

        Optional<EmployeeMasterEntity> optionalEmployee =
                employeeMasterRepository.findById(id);

        EmployeeMasterEntity employeeMasterEntity =
                optionalEmployee.get();

        employeeMasterEntity.setFullName(
                employeeMasterRequestDto.getFullName());

        employeeMasterEntity.setEmployeeCode(
                employeeMasterRequestDto.getEmployeeCode());

        employeeMasterEntity.setFirstName(
                employeeMasterRequestDto.getFirstName());

        employeeMasterEntity.setLastName(
                employeeMasterRequestDto.getLastName());

        employeeMasterEntity.setShiftName(
                employeeMasterRequestDto.getShiftName());
        employeeMasterEntity.setDesignation(
                employeeMasterRequestDto.getDesignation());

        employeeMasterEntity.setDepartment(
                employeeMasterRequestDto.getDepartment());
        employeeMasterEntity.setEmail(
                employeeMasterRequestDto.getEmail());

        employeeMasterEntity.setPhoneNumber(
                employeeMasterRequestDto.getPhoneNumber());

        employeeMasterEntity.setEmergencyContact(
                employeeMasterRequestDto.getEmergencyContact());

        employeeMasterEntity.setAddress(
                employeeMasterRequestDto.getAddress());

        employeeMasterEntity.setDesignationId(
                employeeMasterRequestDto.getDesignationId());

        employeeMasterEntity.setDepartmentId(
                employeeMasterRequestDto.getDepartmentId());

        employeeMasterEntity.setBranchId(
                employeeMasterRequestDto.getBranchId());

        employeeMasterEntity.setReportingManagerId(
                employeeMasterRequestDto.getReportingManagerId());

        employeeMasterEntity.setJoiningDate(
                employeeMasterRequestDto.getJoiningDate());

        employeeMasterEntity.setMonthlySalary(
                employeeMasterRequestDto.getMonthlySalary());

        employeeMasterEntity.setStatus(
                employeeMasterRequestDto.getStatus());

        employeeMasterRepository.save(employeeMasterEntity);

        return "Employee Updated Successfully";
    }

    @Override
    public String deleteEmployee(Long id) {

        if (!employeeMasterRepository.existsById(id)) {
            return "Employee Not Found";
        }

        employeeMasterRepository.deleteById(id);

        return "Employee Deleted Successfully";
    }
}