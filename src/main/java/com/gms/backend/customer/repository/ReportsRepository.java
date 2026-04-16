package com.gms.backend.customer.repository;

import com.gms.backend.customer.dto.AverageCostDTO;
import com.gms.backend.customer.dto.MonthlySpendingDTO;
import com.gms.backend.customer.dto.ServiceHistoryDTO;
import com.gms.backend.customer.entity.ServiceHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReportsRepository extends JpaRepository<ServiceHistory, Long> {

    // ================== UPDATE SERVICE (WITH RATING) ==================
    @Modifying
    @Query("""
    UPDATE ServiceHistory s 
    SET s.serviceName = :serviceName, 
        s.branch = :branch, 
        s.amount = :amount,
        s.rating = :rating
    WHERE s.id = :id
    """)
    int updateServiceFields(
            @Param("id") Long id,
            @Param("serviceName") String serviceName,
            @Param("branch") String branch,
            @Param("amount") Double amount,
            @Param("rating") Integer rating
    );

    // ==================  UPDATE RATING ==================
    @Modifying
    @Query("""
    UPDATE ServiceHistory s 
    SET s.rating = :rating 
    WHERE s.id = :id
    """)
    int updateRatingField(
            @Param("id") Long id,
            @Param("rating") Integer rating
    );

    // ==================  MONTHLY SPENDING ==================
    @Query("""
    SELECT new com.gms.backend.customer.dto.MonthlySpendingDTO(
        FUNCTION('DATE_FORMAT', s.serviceDate, '%Y-%m'),
        SUM(s.amount)
    )
    FROM ServiceHistory s
    WHERE s.serviceDate >= :date
    GROUP BY FUNCTION('DATE_FORMAT', s.serviceDate, '%Y-%m')
    ORDER BY FUNCTION('DATE_FORMAT', s.serviceDate, '%Y-%m') ASC
    """)
    List<MonthlySpendingDTO> getMonthlySpending(
            @Param("date") LocalDate date
    );

    // ==================  AVERAGE COST ==================
    @Query("""
    SELECT new com.gms.backend.customer.dto.AverageCostDTO(
        AVG(s.amount)
    )
    FROM ServiceHistory s
    WHERE s.serviceDate >= :date
    """)
    AverageCostDTO getAverageCost(
            @Param("date") LocalDate date
    );

    // ================== SERVICE HISTORY ==================
    @Query("""
    SELECT new com.gms.backend.customer.dto.ServiceHistoryDTO(
        s.id,
        s.serviceName,
        s.branch,
        s.amount,
        s.rating,
        s.serviceDate
    )
    FROM ServiceHistory s
    WHERE s.serviceDate >= :date
    ORDER BY s.serviceDate DESC
    """)
    List<ServiceHistoryDTO> getServiceHistory(
            @Param("date") LocalDate date
    );


    List<ServiceHistory> findByServiceDateGreaterThanEqual(LocalDate date);

    List<ServiceHistory> findByServiceDateBetween(LocalDate startDate, LocalDate endDate);

    List<ServiceHistory> findAllByOrderByServiceDateDesc();
}