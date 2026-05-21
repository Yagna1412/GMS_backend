package com.gms.backend.inventory.vendors.repository;

import com.gms.backend.inventory.vendors.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {

    List<VendorEntity> findByTier(String tier);

    Long countByTier(String tier);

    List<VendorEntity> findByVendorNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(
            String vendorName,
            String email,
            String phone
    );
}
