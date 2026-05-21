package com.gms.backend.inventory.reports.repository;

import com.gms.backend.inventory.reports.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Long> {
}