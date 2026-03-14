package com.turno.los.controller;

import com.turno.los.dto.DecisionRequest;
import com.turno.los.service.LoanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentController {

    private final LoanService loanService;

    public AgentController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PutMapping("/{agentId}/loans/{loanId}/decision")
    public String decideLoan(@PathVariable Long agentId,
                             @PathVariable Long loanId,
                             @RequestBody DecisionRequest request) {



        loanService.agentDecision(agentId, loanId, request.getDecision());

        return "Decision recorded successfully";
    }
}