package com.gms.backend.customer.feedback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequest {

    private Long customerId;       // who is raising the ticket
    private String issueType;      // e.g. "Billing Issue", "Service Delay"
    private String description;    // full description text
    private String attachmentPath; // optional — file path or URL
}