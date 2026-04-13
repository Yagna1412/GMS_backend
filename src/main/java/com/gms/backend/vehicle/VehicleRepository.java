package com.gms.backend.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    /**
     * Links vehicles.customer_id to the users table
     * @param customer The logged-in customer entity
     * @return An Optional containing the vehicle if found
     */
    Optional<Vehicle> findByCustomer_Id(Long customer);
}