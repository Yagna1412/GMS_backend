package com.gms.backend.customer.profile.dto;

import lombok.Data;

    @Data
    public class ChangePasswordRequest {
        private String oldPassword;
        private String newPassword;
    }


