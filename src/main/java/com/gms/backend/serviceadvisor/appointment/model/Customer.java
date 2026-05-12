package com.gms.backend.serviceadvisor.appointment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must be less than 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must be less than 100 characters")
    private String lastName;

    @Size(max = 20, message = "Phone must be less than 20 characters")
    private String phone;

    @Email(message = "Email must be valid")
    @Size(max = 255, message = "Email must be less than 255 characters")
    private String email;

    @Size(max = 500, message = "Address must be less than 500 characters")
    private String address;

    @CreationTimestamp
    private LocalDateTime createdAt;
}