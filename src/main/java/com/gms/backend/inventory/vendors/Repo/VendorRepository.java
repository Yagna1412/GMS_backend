package com.gms.backend.inventory.vendors.Repo;

import com.gms.backend.inventory.vendors.Entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
}