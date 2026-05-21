package com.gms.backend.inventory.dashboard.repository;

import com.gms.backend.inventory.dashboard.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCurrentStockLessThanEqualAndCurrentStockGreaterThan(
            Integer minimumStock,
            Integer currentStock
    );

    List<Item> findByCurrentStock(Integer stock);

    Long countByStatus(String status);
}
