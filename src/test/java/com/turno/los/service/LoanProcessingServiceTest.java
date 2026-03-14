package com.turno.los.service;

import com.turno.los.entity.*;
import com.turno.los.repository.*;
import com.turno.los.service.impl.LoanProcessingServiceImpl;
import com.turno.los.service.NotificationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoanProcessingServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private AgentRepository agentRepository;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private LoanProcessingServiceImpl loanProcessingService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessLoan_SystemApproved() {

        Loan loan = new Loan();
        loan.setLoanAmount(20000.0);
        loan.setApplicationStatus(LoanStatus.APPLIED);

        loanProcessingService.processLoan(loan);

        assertEquals(LoanStatus.APPROVED_BY_SYSTEM,
                loan.getApplicationStatus());


    }

    @Test
    void testProcessLoan_SystemRejected() {

        Loan loan = new Loan();
        loan.setLoanAmount(2000000.0);
        loan.setApplicationStatus(LoanStatus.APPLIED);

        loanProcessingService.processLoan(loan);

        assertEquals(LoanStatus.REJECTED_BY_SYSTEM,
                loan.getApplicationStatus());
    }

    @Test
    void testProcessLoan_UnderReview() {

        Loan loan = new Loan();
        loan.setLoanAmount(100000.0);
        loan.setApplicationStatus(LoanStatus.APPLIED);

        Agent agent = new Agent();
        agent.setAgentId(1L);

        when(agentRepository.findFirstByAvailableTrue())
                .thenReturn(Optional.of(agent));

        loanProcessingService.processLoan(loan);

        assertEquals(LoanStatus.UNDER_REVIEW,
                loan.getApplicationStatus());

        verify(notificationService)
                .notifyAgent(any(), any());
    }
}