package com.turno.los.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "loan",
        indexes = {
                @Index(name = "idx_status", columnList = "application_status"),
                @Index(name = "idx_customer", columnList = "customer_name")
        }
)
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String customerName;

    private String customerPhone;

    private Double loanAmount;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @Enumerated(EnumType.STRING)
    private LoanStatus applicationStatus;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;
}