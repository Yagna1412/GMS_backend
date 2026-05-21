package com.gms.backend.inventory.items.repository;

import com.gms.backend.inventory.items.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository
        extends JpaRepository<InventoryItem, Integer> {

    List<InventoryItem>
    findByItemNameContainingIgnoreCase(String itemName);

    List<InventoryItem>
    findBySkuContainingIgnoreCase(String sku);

    List<InventoryItem>
    findByStatus(String status);

    long countByStatus(String status);
}