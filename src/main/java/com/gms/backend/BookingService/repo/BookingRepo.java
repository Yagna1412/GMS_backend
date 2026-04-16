package com.gms.backend.BookingService.repo;

import com.gms.backend.BookingService.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<BookingEntity, Long> {

    List<BookingEntity> findByBranch_Id(Long branchId);

    List<BookingEntity> findByBookingStatus(String status);
}