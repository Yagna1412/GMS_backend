package com.gms.backend.jobcard.myjobs.repository;
import com.gms.backend.jobcard.myjobs.entity.JobDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyJobsRepository extends JpaRepository<JobDetails, Long> {

    List<JobDetails> findByStatus(String status);

}