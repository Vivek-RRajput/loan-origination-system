package com.turno.los.repository;

import com.turno.los.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findFirstByAvailableTrue();
    Optional<Agent> findById(Long id);
}