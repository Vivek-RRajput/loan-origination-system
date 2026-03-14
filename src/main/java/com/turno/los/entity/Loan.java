package com.turno.los.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
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

    @NonNull
    private String customerPhone;

    @NotNull
    @Positive
    private Double loanAmount;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @Enumerated(EnumType.STRING)
    private LoanStatus applicationStatus;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @Version
    private Long version;
}