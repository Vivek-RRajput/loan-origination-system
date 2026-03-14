package com.turno.los.service.impl;

import com.turno.los.entity.Agent;
import com.turno.los.entity.Loan;
import com.turno.los.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notifyAgent(Agent agent, Loan loan) {

        System.out.println("Push notification sent to Agent: "
                + agent.getName() + " for loan: " + loan.getLoanId());

        if(agent.getManager() != null) {

            System.out.println("Notification sent to Manager: "
                    + agent.getManager().getName());
        }
    }

    @Override
    public void notifyCustomer(Loan loan) {

        System.out.println("SMS sent to customer: "
                + loan.getCustomerPhone() +
                " Loan approved.");
    }
}