package com.turno.los.service;

import com.turno.los.entity.Agent;
import com.turno.los.entity.Loan;

public interface NotificationService {

    void notifyAgent(Agent agent, Loan loan);

    void notifyCustomer(Loan loan);
}