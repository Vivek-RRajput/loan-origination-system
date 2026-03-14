package com.turno.los.dto;

import com.turno.los.entity.LoanType;
import lombok.Data;

@Data
public class LoanRequest {

    private String customerName;

    private String customerPhone;

    private Double loanAmount;

    private LoanType loanType;
}