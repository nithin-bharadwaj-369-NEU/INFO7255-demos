package com.bharadwaj.demotwo.service;

import com.bharadwaj.demotwo.model.Plan;
import com.bharadwaj.demotwo.repository.MedicalPlanRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MedicalPlanServiceImpl implements MedicalPlanService{

    @Autowired
    private MedicalPlanRepository medicalPlanRepository;

    @Autowired
    private AmqpTemplate myRabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;


    @Override
    public boolean savePlan(Plan p) {
        boolean result = medicalPlanRepository.savePlan(p);
        if (result) {
            myRabbitTemplate.convertAndSend(exchange, routingkey, "Plan saved: " + p.toString());
        }
        return result;
    }

    @Override
    public List<Plan> fetchAllPlans() {
        return medicalPlanRepository.fetchAllPlans();
    }

    @Override
    public Optional<Plan> getMedicalPlanById(String objectId) {
        return medicalPlanRepository.getPlanById(objectId);
    }

    @Override
    public Optional<Boolean> deletePlan(String objectId) {
        Optional<Boolean> result = medicalPlanRepository.deletePlan(objectId);
        if (result.isPresent() && result.get()) {
            myRabbitTemplate.convertAndSend(exchange, routingkey, "Deleted plan with id: " + objectId);
        }
        return result;
    }

    @Override
    public Optional<Boolean> updatePlan(String objectId, Plan p) {
        Optional<Boolean> result = medicalPlanRepository.updatePlanById(objectId, p);
        if (result.isPresent() && result.get()) {
            myRabbitTemplate.convertAndSend(exchange, routingkey, "Updated plan: " + p.toString());
        }
        return result;
    }

    @Override
    public Optional<Plan> patchMedicalPlan(String objectId, ObjectNode updates) throws IOException {
        Optional<Plan> patchedPlan = medicalPlanRepository.patchPlan(objectId, updates);
        if (patchedPlan.isPresent()) {
            myRabbitTemplate.convertAndSend(exchange, routingkey, "Patched plan with id: " + objectId);
        }
        return patchedPlan;
    }
}
