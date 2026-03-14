package com.turno.los.service;

import com.turno.los.dto.LoanRequest;
import com.turno.los.entity.Loan;
import com.turno.los.entity.LoanStatus;
import com.turno.los.entity.LoanType;
import com.turno.los.repository.LoanRepository;

import com.turno.los.service.impl.LoanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLoan() {

        // request object
        LoanRequest request = new LoanRequest();
        request.setCustomerName("Vivek");
        request.setCustomerPhone("9999999999");
        request.setLoanAmount(50000.0);
        request.setLoanType(LoanType.PERSONAL);

        // saved loan entity
        Loan savedLoan = new Loan();
        savedLoan.setCustomerName("Vivek");

        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);

        Loan result = loanService.createLoan(request);

        assertEquals("Vivek", result.getCustomerName());

        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void testFindLoansByStatus() {

        Loan loan = new Loan();
        loan.setCustomerName("Ram");

        Page<Loan> page = new PageImpl<>(List.of(loan));

        when(loanRepository.findByApplicationStatus(any(), any()))
                .thenReturn(page);

        Page<Loan> result =
                loanService.getLoansByStatus(
                        LoanStatus.APPLIED, 0, 1);

        assertEquals(1, result.getContent().size());

        verify(loanRepository).findByApplicationStatus(any(), any());
    }
}