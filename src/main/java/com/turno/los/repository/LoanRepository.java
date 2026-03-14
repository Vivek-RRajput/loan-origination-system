package com.turno.los.repository;

import com.turno.los.entity.Loan;
import com.turno.los.entity.LoanStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByApplicationStatus(LoanStatus status);

    @Query("""
            SELECT l.applicationStatus, COUNT(l)
            FROM Loan l
            GROUP BY l.applicationStatus
            """)
    List<Object[]> getLoanStatusCounts();

    @Query("""
            SELECT l.customerName, COUNT(l)
            FROM Loan l
            WHERE l.applicationStatus IN (
            'APPROVED_BY_SYSTEM',
            'APPROVED_BY_AGENT'
            )
            GROUP BY l.customerName
            ORDER BY COUNT(l) DESC
            """)
    List<Object[]> findTopCustomers(Pageable pageable);

    Page<Loan> findByApplicationStatus(
            LoanStatus status,
            Pageable pageable
    );
}