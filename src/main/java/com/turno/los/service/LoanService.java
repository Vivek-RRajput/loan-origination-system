package com.turno.los.service;

import com.turno.los.dto.LoanRequest;
import com.turno.los.entity.Loan;
import com.turno.los.entity.LoanStatus;
import org.springframework.data.domain.Page;

import java.util.*;

public interface LoanService {

    Loan createLoan(LoanRequest request);

    void agentDecision(Long agentId, Long loanId, String decision);

    Map<String, Long> getLoanStatusCounts();

    List<Object[]> getTopCustomers();

    Page<Loan> getLoansByStatus(
            LoanStatus status,
            int page,
            int size
    );
}