package com.gms.backend.hr.employeemaster.repository;

import com.gms.backend.hr.employeemaster.entity.EmployeeMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMasterRepository
        extends JpaRepository<EmployeeMasterEntity, Long> {

}
