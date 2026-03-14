package com.turno.los.controller;

import com.turno.los.service.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final LoanService loanService;

    public CustomerController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/top")
    public List<Object[]> topCustomers() {

        return loanService.getTopCustomers();
    }
}