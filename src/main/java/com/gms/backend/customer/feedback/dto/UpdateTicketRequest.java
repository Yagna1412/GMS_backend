package com.gms.backend.customer.feedback.dto;

import com.gms.backend.customer.feedback.entity.SupportTicket.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTicketRequest {

    private String issueType;      // optional — update issue type
    private String description;    // optional — update description
    private String attachmentPath; // optional — update attachment
    private TicketStatus status;   // optional — update status
}