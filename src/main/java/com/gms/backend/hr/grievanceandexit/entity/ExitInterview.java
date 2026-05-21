package com.gms.backend.hr.grievanceandexit.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exit_interviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExitInterview {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long exitId;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    private Boolean rehireEligible;
}
