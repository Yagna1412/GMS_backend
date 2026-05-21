package com.gms.backend.inventory.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderRequestDTO {

    private Long itemId;

    private Integer quantity;

    private String vendorName;

    private String remarks;
}
