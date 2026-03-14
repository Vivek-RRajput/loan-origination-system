package com.turno.los.service.impl;

import com.turno.los.entity.Agent;
import com.turno.los.entity.Loan;
import com.turno.los.entity.LoanStatus;
import com.turno.los.repository.AgentRepository;
import com.turno.los.repository.LoanRepository;
import com.turno.los.service.LoanProcessingService;
import com.turno.los.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;

@Service
public class LoanProcessingServiceImpl implements LoanProcessingService {

    private final LoanRepository loanRepository;
    private final ExecutorService executorService;

    private final AgentRepository agentRepository;
    private final NotificationService notificationService;

    public LoanProcessingServiceImpl(
            LoanRepository loanRepository,
            ExecutorService executorService,
            AgentRepository agentRepository,
            NotificationService notificationService) {

        this.loanRepository = loanRepository;
        this.executorService = executorService;
        this.agentRepository = agentRepository;
        this.notificationService = notificationService;
    }

    @Override
    public void processLoans() {

        List<Loan> loans =
                loanRepository.findByApplicationStatus(LoanStatus.APPLIED);

        for (Loan loan : loans) {

            executorService.submit(() -> processLoan(loan));
        }
    }

    private void processLoan(Loan loan) {

        try {

            Thread.sleep(25000);

            if (loan.getLoanAmount() < 50000) {

                loan.setApplicationStatus(LoanStatus.APPROVED_BY_SYSTEM);

            } else if (loan.getLoanAmount() > 200000) {

                loan.setApplicationStatus(LoanStatus.REJECTED_BY_SYSTEM);

            } else {

                loan.setApplicationStatus(LoanStatus.UNDER_REVIEW);

                Agent agent = agentRepository
                        .findFirstByAvailableTrue()
                        .orElse(null);

                if(agent != null) {

                    loan.setAgent(agent);

                    notificationService.notifyAgent(agent, loan);
                }
            }

            loanRepository.save(loan);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}