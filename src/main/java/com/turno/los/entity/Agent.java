package com.turno.los.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;

    private String name;

    private Boolean available = true;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Agent manager;
}