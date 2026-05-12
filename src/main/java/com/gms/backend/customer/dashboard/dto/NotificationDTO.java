package com.gms.backend.customer.dashboard.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private String message;
    private String time;
}