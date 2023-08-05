package com.bharadwaj.demotwo.service;

import com.bharadwaj.demotwo.model.Plan;
import com.bharadwaj.demotwo.repository.MedicalPlanRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageBuilder;
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

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean savePlan(Plan p) {
        boolean result = medicalPlanRepository.savePlan(p);
        if (result) {
            try {
                String planJson = objectMapper.writeValueAsString(p);
                myRabbitTemplate.convertAndSend(exchange, routingkey,
                        MessageBuilder.withBody((planJson).getBytes())
                                .setHeader("action", "CREATE")
                                .build());
            } catch (JsonProcessingException e) {
                log.error("Error converting plan to JSON in savePlan Method", e);
            }
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
    public Optional<Boolean> deletePlan(String objectId) throws JsonProcessingException {
        // Getting the plan info to push the data to Elastic search and queue
        Optional<Plan> planInfo = medicalPlanRepository.getPlanById(objectId);
        Optional<Boolean> result = medicalPlanRepository.deletePlan(objectId);
        if (result.isPresent() && result.get()) {
            if (planInfo.isPresent()) {
                String planJson = objectMapper.writeValueAsString(planInfo.get());
                myRabbitTemplate.convertAndSend(exchange, routingkey, MessageBuilder.withBody(planJson.getBytes())
                        .setHeader("action", "DELETE")
                        .build());
            }
        }
        return result;
    }

    @Override
    public Optional<Boolean> updatePlan(String objectId, Plan p) {
        Optional<Boolean> result = medicalPlanRepository.updatePlanById(objectId, p);
        if (result.isPresent() && result.get()) {
            try {
                Optional<Plan> planInfo = medicalPlanRepository.getPlanById(objectId);
                if (planInfo.isPresent()) {
                    String planJson = objectMapper.writeValueAsString(planInfo.get());
                    myRabbitTemplate.convertAndSend(exchange, routingkey, MessageBuilder.withBody(planJson.getBytes())
                            .setHeader("action", "DELETE")
                            .build());
                }
                String planJson = objectMapper.writeValueAsString(p);
                myRabbitTemplate.convertAndSend(exchange, routingkey,
                        MessageBuilder.withBody((planJson).getBytes())
                                .setHeader("action", "PUT")
                                .build());
            } catch (JsonProcessingException e) {
                log.error("Error converting plan to JSON in updatePlan Method", e);
            }
        }
        return result;
    }

    @Override
    public Optional<Plan> patchMedicalPlan(String objectId, ObjectNode updates) throws IOException {
        Optional<Plan> patchedPlan = medicalPlanRepository.patchPlan(objectId, updates);
        if (patchedPlan.isPresent()) {
            try {
                String planJson = objectMapper.writeValueAsString(patchedPlan.get());
                myRabbitTemplate.convertAndSend(exchange, routingkey,
                        MessageBuilder.withBody((planJson).getBytes())
                                .setHeader("action", "PATCH")
                                .build());
            } catch (JsonProcessingException e) {
                log.error("Error converting plan to JSON in patchMedicalPlan Method", e);
            }
        }
        return patchedPlan;
    }
}
