package com.gms.backend.jobcard.myjobs.repository;

import com.gms.backend.jobcard.myjobs.entity.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyJobsRepository extends JpaRepository<JobDetails, Long> {

    @Query("SELECT j FROM JobDetails j WHERE j.status = :status")
    List<JobDetails> findByStatus(@Param("status") String status);
}