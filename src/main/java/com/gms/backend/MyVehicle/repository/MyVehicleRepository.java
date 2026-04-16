package com.gms.backend.MyVehicle.repository;

import com.gms.backend.MyVehicle.entity.MyVehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyVehicleRepository extends JpaRepository<MyVehicleEntity, Long> {

    List<MyVehicleEntity> findByMakeContainingIgnoreCaseOrModelContainingIgnoreCase(
            String make, String model
    );
    List<MyVehicleEntity> findByCustomerId(Long CustomerId);



}