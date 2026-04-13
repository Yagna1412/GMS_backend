package com.gms.backend.customer.profile.repositary;

import com.gms.backend.customer.profile.entity.ProfileDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<ProfileDetails,Long> {
}
