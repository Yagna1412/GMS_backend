package com.gms.backend.jobcard.Repository;

import com.gms.backend.customer.Customer;
import com.gms.backend.jobcard.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // This fixed the error on line 42 of your service
    int countByCustomerAndStatus(Customer customer, String status);
}