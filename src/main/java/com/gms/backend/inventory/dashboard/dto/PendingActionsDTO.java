package com.gms.backend.inventory.dashboard.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PendingActionsDTO {

    private Long purchaseOrdersPending;

    private Long poApprovalsPending;

    private Long vendorInvoicesPending;

    private Long stockAuditsDue;
}
