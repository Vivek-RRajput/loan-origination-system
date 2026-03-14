package com.turno.los.controller;

import com.turno.los.dto.LoanRequest;
import com.turno.los.entity.Loan;
import com.turno.los.entity.LoanStatus;
import com.turno.los.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan submitLoan(@Valid @RequestBody LoanRequest request) {
        return loanService.createLoan(request);
    }

    @GetMapping("/status-count")
    public Map<String, Long> getStatusCounts() {

        return loanService.getLoanStatusCounts();
    }

    @GetMapping
    public Page<Loan> getLoansByStatus(
            @RequestParam LoanStatus status,
            @RequestParam int page,
            @RequestParam int size) {

        return loanService
                .getLoansByStatus(status, page, size);
    }
}