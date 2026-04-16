package com.gms.backend.BookingService.repo;

import com.gms.backend.BookingService.entity.BranchEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepo extends JpaRepository<BranchEntity, Long> {

    List<BranchEntity> findByBranchNameContainingIgnoreCaseOrBranchCityContainingIgnoreCaseOrBranchZipcode(
            String name, String city, String zip);

    @Query(value = "SELECT * FROM branch WHERE " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(branch_latitude)) * " +
            "cos(radians(branch_longitude) - radians(:lng)) + " +
            "sin(radians(:lat)) * sin(radians(branch_latitude)))) < :radius",
            nativeQuery = true)
    List<BranchEntity> findNearbyBranches(@Param("lat") Double lat,
                                          @Param("lng") Double lng,
                                          @Param("radius") Double radius);
}