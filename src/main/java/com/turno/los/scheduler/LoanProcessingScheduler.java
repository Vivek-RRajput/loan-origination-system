package com.turno.los.scheduler;

import com.turno.los.service.LoanProcessingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoanProcessingScheduler {

    private final LoanProcessingService loanProcessingService;

    public LoanProcessingScheduler(LoanProcessingService loanProcessingService) {
        this.loanProcessingService = loanProcessingService;
    }

    @Scheduled(fixedDelay = 30000)
    public void runProcessor() {

        loanProcessingService.processLoans();
    }
}