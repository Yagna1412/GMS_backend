package com.gms.backend.serviceadvisor.dashboard.repository;


import com.gms.backend.serviceadvisor.dashboard.entity.Complaint;
import com.gms.backend.serviceadvisor.dashboard.enums.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    long countByStatusIn(List<ComplaintStatus> statuses);

    List<Complaint> findByStatusInOrderByIdDesc(List<ComplaintStatus> statuses);
}
