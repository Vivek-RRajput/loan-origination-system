package com.turno.los.service.impl;

import com.turno.los.dto.LoanRequest;
import com.turno.los.entity.Agent;
import com.turno.los.entity.Loan;
import com.turno.los.entity.LoanStatus;
import com.turno.los.repository.AgentRepository;
import com.turno.los.repository.LoanRepository;
import com.turno.los.service.LoanService;
import com.turno.los.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final AgentRepository agentRepository;
    private final NotificationService notificationService;

    public LoanServiceImpl(LoanRepository loanRepository,
                           AgentRepository agentRepository,
                           NotificationService notificationService) {

        this.loanRepository = loanRepository;
        this.agentRepository = agentRepository;
        this.notificationService = notificationService;
    }

    @Override
    public Loan createLoan(LoanRequest request) {

        Loan loan = new Loan();

        loan.setCustomerName(request.getCustomerName());
        loan.setCustomerPhone(request.getCustomerPhone());
        loan.setLoanAmount(request.getLoanAmount());
        loan.setLoanType(request.getLoanType());
        loan.setApplicationStatus(LoanStatus.APPLIED);
        loan.setCreatedAt(LocalDateTime.now());

        return loanRepository.save(loan);
    }

    @Override
    public void agentDecision(Long agentId, Long loanId, String decision) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        if (!loan.getAgent().getAgentId().equals(agentId)) {

            throw new RuntimeException("Agent not assigned to this loan");
        }

        if ("APPROVE".equalsIgnoreCase(decision)) {

            loan.setApplicationStatus(LoanStatus.APPROVED_BY_AGENT);

            notificationService.notifyCustomer(loan);

        } else {

            loan.setApplicationStatus(LoanStatus.REJECTED_BY_AGENT);
        }

        loanRepository.save(loan);
    }

    @Override
    public Map<String, Long> getLoanStatusCounts() {

        List<Object[]> results = loanRepository.getLoanStatusCounts();

        Map<String, Long> map = new HashMap<>();

        for (Object[] row : results) {

            map.put(row[0].toString(), (Long) row[1]);
        }

        return map;
    }

    @Override
    public List<Object[]> getTopCustomers() {

        Pageable pageable = PageRequest.of(0,3);

        return loanRepository.findTopCustomers(pageable);
    }

    @Override
    public Page<Loan> getLoansByStatus(
            LoanStatus status,
            int page,
            int size) {
        size = Math.min(size, 50); //to handle a pagination limits
        Pageable pageable = PageRequest.of(page, size);

        return loanRepository
                .findByApplicationStatus(status, pageable);
    }
}