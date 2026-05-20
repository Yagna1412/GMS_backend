package com.gms.backend.inventory.stockmanagement.repository;

import com.gms.backend.inventory.stockmanagement.entity.StockMovement;
import com.gms.backend.inventory.stockmanagement.entity.StockMovement.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

    // All movements newest first
    List<StockMovement> findAllByOrderByDateTimeDesc();

    // Filter by type
    List<StockMovement> findByMovementTypeOrderByDateTimeDesc(
            MovementType movementType
    );
    // Search by SKU, item name, reference — search bar
    @Query("""
        SELECT s FROM StockMovement s
        WHERE LOWER(s.itemSku)         LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(s.itemName)        LIKE LOWER(CONCAT('%', :q, '%'))
           OR LOWER(s.referenceId) LIKE LOWER(CONCAT('%', :q, '%'))
        ORDER BY s.dateTime DESC
    """)
    List<StockMovement> search(@Param("q") String q);

    // Summary counts — 4 cards
    @Query(value = """
        SELECT
            COUNT(*),
            SUM(CASE WHEN movement_type = 'INWARD'     THEN 1 ELSE 0 END),
            SUM(CASE WHEN movement_type = 'OUTWARD'    THEN 1 ELSE 0 END),
            SUM(CASE WHEN movement_type = 'ADJUSTMENT' THEN 1 ELSE 0 END)
        FROM stock_movements
    """, nativeQuery = true)
    Object[] getSummary();
}