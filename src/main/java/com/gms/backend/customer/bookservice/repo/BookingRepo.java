package com.gms.backend.customer.bookservice.repo;

import com.gms.backend.customer.bookservice.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<BookingEntity, Long> {

    List<BookingEntity> findByBranch_Id(Long branchId);

    List<BookingEntity> findByBookingStatus(String status);
}