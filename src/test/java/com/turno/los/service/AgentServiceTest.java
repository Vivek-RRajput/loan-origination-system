package com.turno.los.service;

import com.turno.los.entity.*;
import com.turno.los.repository.*;

import com.turno.los.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AgentServiceTest {

    @Mock
    private LoanRepository loanRepository;
    
    @Mock
    private  AgentRepository agentRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private LoanServiceImpl loanService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAgentApproveLoan() {

        Loan loan = new Loan();
        loan.setApplicationStatus(LoanStatus.UNDER_REVIEW);

        Agent agent = new Agent();
        agent.setAgentId(1L);

        // assign agent to loan
        loan.setAgent(agent);

        when(loanRepository.findById(1L))
                .thenReturn(Optional.of(loan));

        when(agentRepository.findById(1L))
                .thenReturn(Optional.of(agent));

        loanService.agentDecision(1L, 1L, "APPROVE");

        assertEquals(
                LoanStatus.APPROVED_BY_AGENT,
                loan.getApplicationStatus()
        );
    }

    @Test
    void testAgentRejectLoan() {

        Loan loan = new Loan();
        loan.setApplicationStatus(LoanStatus.UNDER_REVIEW);

        Agent agent = new Agent();
        agent.setAgentId(1L);

        loan.setAgent(agent);

        when(loanRepository.findById(1L))
                .thenReturn(Optional.of(loan));

        when(agentRepository.findById(1L))
                .thenReturn(Optional.of(agent));

        loanService.agentDecision(1L, 1L, "REJECT");

        assertEquals(
                LoanStatus.REJECTED_BY_AGENT,
                loan.getApplicationStatus()
        );
    }

    @Test
    void testAgentDecision_InvalidStatus() {

        Loan loan = new Loan();
        loan.setApplicationStatus(LoanStatus.APPROVED_BY_SYSTEM);

        when(loanRepository.findById(1L))
                .thenReturn(Optional.of(loan));

        assertThrows(RuntimeException.class,
                () -> loanService.agentDecision(1L, 1L, "APPROVE"));
    }
}