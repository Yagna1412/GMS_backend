package com.gms.backend.inventory.purchaseorders.Repo;

import com.gms.backend.inventory.purchaseorders.Entity.PurchaseOrderEntity;
import com.gms.backend.inventory.purchaseorders.Entity.PurchaseOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Long> {

    // Filter by status
    List<PurchaseOrderEntity> findByStatus(PurchaseOrderStatus status);

    // Search by PO number or vendor name — native SQL, no VendorEntity dependency
    @Query(value = """
        SELECT po.* FROM purchase_orders po
        JOIN vendors v ON v.id = po.vendor_id
        WHERE LOWER(po.po_number) LIKE LOWER(CONCAT('%', :query, '%'))
           OR LOWER(v.vendor_name) LIKE LOWER(CONCAT('%', :query, '%'))
        """, nativeQuery = true)
    List<PurchaseOrderEntity> searchByPoNumberOrVendorName(@Param("query") String query);

    // Filter by status AND search — native SQL
    @Query(value = """
        SELECT po.* FROM purchase_orders po
        JOIN vendors v ON v.id = po.vendor_id
        WHERE po.status = :status
          AND (LOWER(po.po_number) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(v.vendor_name) LIKE LOWER(CONCAT('%', :query, '%')))
        """, nativeQuery = true)
    List<PurchaseOrderEntity> findByStatusAndSearch(
            @Param("status") String status,
            @Param("query") String query);

    // PO number generation — count POs in a given year
    @Query(value = "SELECT COUNT(*) FROM purchase_orders WHERE YEAR(order_date) = :year", nativeQuery = true)
    long countByYear(@Param("year") int year);

    // Stats counts
    long countByStatus(PurchaseOrderStatus status);

    // Latest PO — for sequential number generation fallback
    Optional<PurchaseOrderEntity> findTopByOrderByIdDesc();
}