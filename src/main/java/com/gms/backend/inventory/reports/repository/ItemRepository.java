package com.gms.backend.inventory.reports.repository;

import com.gms.backend.inventory.reports.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository
        extends JpaRepository<Item, Long> {

    boolean existsBySku(String sku);
}