package com.gms.backend.inventory.stockmanagement.service;

import com.gms.backend.inventory.stockmanagement.dto.*;
import com.gms.backend.inventory.stockmanagement.entity.StockMovement;
import com.gms.backend.inventory.stockmanagement.entity.StockMovement.MovementType;
import com.gms.backend.inventory.stockmanagement.repository.StockMovementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockMovementService {

    private final StockMovementRepository repo;

    // ══════════════════════════════════════════════════════════════════════
    // TEMPORARY: Hardcoded item data
    // Used by: getItemsForDropdown() and resolveItem()
    //
    // LATER — when colleague's ItemRepository is available:
    //   1. Add:  private final ItemRepository itemRepo;
    //   2. Replace getItemsForDropdown() body with:
    //            return itemRepo.findAll().stream()
    //                .map(i -> new ItemDropdownDto(i.getId(), i.getSku(), i.getName()))
    //                .collect(Collectors.toList());
    //   3. Replace resolveItem() body with:
    //            return itemRepo.findById(itemId)
    //                .map(i -> new String[]{ i.getSku(), i.getName() })
    //                .orElseThrow(() -> new RuntimeException("Item not found: " + itemId));
    // ══════════════════════════════════════════════════════════════════════
    private static final List<ItemDropdownDto> TEMP_ITEMS = Arrays.asList(
            new ItemDropdownDto(1L, "FILTER-AIR-003", "Air Filter"),
            new ItemDropdownDto(2L, "OIL-5W30-001",   "Engine Oil 5W-30"),
            new ItemDropdownDto(3L, "BRAKE-PAD-002",   "Brake Pad Set"),
            new ItemDropdownDto(4L, "SPARK-PLG-004",   "Spark Plug"),
            new ItemDropdownDto(5L, "BELT-ALT-005",    "Alternator Belt")
    );

    private static final Map<Long, ItemDropdownDto> TEMP_ITEMS_MAP =
            TEMP_ITEMS.stream().collect(Collectors.toMap(ItemDropdownDto::getId, i -> i));

    // Items dropdown — all 3 modals
    public List<ItemDropdownDto> getItemsForDropdown() {
        return TEMP_ITEMS; // LATER: replace with itemRepo.findAll() — see comment above
    }

    // Internal: get SKU + name from itemId
    private String[] resolveItem(Long itemId) {
        // LATER: replace with itemRepo.findById() — see comment above
        ItemDropdownDto item = TEMP_ITEMS_MAP.get(itemId);
        if (item == null) {
            throw new RuntimeException("Item not found with id: " + itemId);
        }
        return new String[]{ item.getSku(), item.getName() };
    }

    // ══════════════════════════════════════════════════════════════════════
    // TEMPORARY: createdBy is hardcoded as "Inventory Manager"
    // LATER — when Spring Security is set up:
    //   Replace "Inventory Manager" in controller with:
    //   SecurityContextHolder.getContext().getAuthentication().getName()
    // ══════════════════════════════════════════════════════════════════════

    // ── Summary cards ──────────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public StockSummaryDTO getSummary() {
        Object[] result = repo.getSummary();
        Object[] row    = (Object[]) result[0];
        long total       = row[0] != null ? ((Number) row[0]).longValue() : 0L;
        long inward      = row[1] != null ? ((Number) row[1]).longValue() : 0L;
        long outward     = row[2] != null ? ((Number) row[2]).longValue() : 0L;
        long adjustments = row[3] != null ? ((Number) row[3]).longValue() : 0L;
        return new StockSummaryDTO(total, inward, outward, adjustments);
    }

    // ── All movements — main table ─────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<StockMovementDto> getAllMovements() {
        return repo.findAllByOrderByDateTimeDesc()
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    // ── Filter by type — dropdown ──────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<StockMovementDto> getByType(String type) {
        MovementType movementType;
        try {
            movementType = MovementType.valueOf(type.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Invalid type '" + type + "'. Allowed: INWARD, OUTWARD, ADJUSTMENT");
        }
        return repo.findByMovementTypeOrderByDateTimeDesc(movementType)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    // ── Search — search bar ────────────────────────────────────────────────
    @Transactional(readOnly = true)
    public List<StockMovementDto> search(String q) {
        return repo.search(q.trim())
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    // ── GRN / Inward ──────────────────────────────────────────────────────
    @Transactional
    public StockMovementDto createGrn(GrnCreateDto dto, String createdBy) {
        Long itemId = dto.getItemId();
        if (itemId == null) throw new IllegalArgumentException("itemId is required");
        String[] item = resolveItem(itemId);
        StockMovement movement = StockMovement.builder()
                .itemId(itemId)
                .itemSku(item[0])
                .itemName(item[1])
                .quantity(dto.getQuantity())
                .movementType(MovementType.INWARD)
                .referenceId(dto.getReferenceId())
                .createdBy(createdBy)
                .notes(dto.getNotes())
                .build();
        log.info("GRN created: ref={}", dto.getReferenceId());
        return toDto(repo.save(movement));
    }

    // ── Issue Parts / Outward ─────────────────────────────────────────────
    @Transactional
    public StockMovementDto createIssueParts(IssuePartsCreateDto dto, String createdBy) {
        Long itemId = dto.getItemId();
        if (itemId == null) throw new IllegalArgumentException("itemId is required");
        String[] item = resolveItem(itemId);
        StockMovement movement = StockMovement.builder()
                .itemId(itemId)
                .itemSku(item[0])
                .itemName(item[1])
                .quantity(dto.getQuantity())
                .movementType(MovementType.OUTWARD)
                .referenceId(dto.getReferenceId())
                .createdBy(createdBy)
                .notes(dto.getNotes())
                .build();
        log.info("Issue Parts: ref={}", dto.getReferenceId());
        return toDto(repo.save(movement));
    }

    // ── Stock Adjustment ──────────────────────────────────────────────────
    @Transactional
    public StockMovementDto createAdjustment(AdjustmentCreateDto dto, String createdBy) {
        Long itemId = dto.getItemId();
        if (itemId == null) throw new IllegalArgumentException("itemId is required");
        String[] item = resolveItem(itemId);
        StockMovement movement = StockMovement.builder()
                .itemId(itemId)
                .itemSku(item[0])
                .itemName(item[1])
                .quantity(dto.getQuantity())        // negative allowed
                .movementType(MovementType.ADJUSTMENT)
                .referenceId(dto.getReferenceId())
                .createdBy(createdBy)
                .notes(dto.getNotes())              // mandatory reason
                .build();
        log.info("Adjustment: ref={}", dto.getReferenceId());
        return toDto(repo.save(movement));
    }

    // ── Export ────────────────────────────────────────────────────────────
//    @Transactional(readOnly = true)
//    public List<StockMovementDto> getAllForExport(String type) {
//        if (type != null && !type.isBlank()) {
//            return getByType(type);
//        }
//        return getAllMovements();
//    }

    // ── Mapper ────────────────────────────────────────────────────────────
    private StockMovementDto toDto(StockMovement s) {
        return StockMovementDto.builder()
                .id(s.getId())
                .itemSku(s.getItemSku())
                .itemName(s.getItemName())
                .quantity(s.getQuantity())
                .movementType(s.getMovementType() != null ? s.getMovementType().name() : null)
                .referenceId(s.getReferenceId())
                .createdBy(s.getCreatedBy())
                .notes(s.getNotes())
                .dateTime(s.getDateTime())
                .build();
    }
}