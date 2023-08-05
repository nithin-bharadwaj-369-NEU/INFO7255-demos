package com.bharadwaj.demotwo.service;

import com.bharadwaj.demotwo.model.Plan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
@Slf4j
@Component
public class PlanRabbitListener {

    @Autowired
    private PlanElasticsearchService planElasticsearchService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void listen(Message message) throws IOException {
        String action = message.getMessageProperties().getHeaders().get("action").toString();
        Object planInfo = null;
        String messageData = new String(message.getBody());
        planInfo = objectMapper.readValue(messageData, Plan.class);

        switch (action) {
            case "CREATE":
                // Parse the planInfo and index to Elasticsearch
                planElasticsearchService.postDocument(new JSONObject(objectMapper.writeValueAsString(planInfo)));
                break;
            case "PATCH":
                // Parse the planInfo, update in Elasticsearch
                planElasticsearchService.postDocument(new JSONObject(objectMapper.writeValueAsString(planInfo)));
                break;
            case "PUT":
                // Then insert the document as a new plan
                planElasticsearchService.postDocument(new JSONObject(objectMapper.writeValueAsString(planInfo)));
                break;
            case "DELETE":
                // Parse the planInfo, delete from Elasticsearch
                planElasticsearchService.deleteDocument(new JSONObject(objectMapper.writeValueAsString(planInfo)));
                break;
            default:
                System.out.println("Unknown action: " + action);
                break;
        }
    }
}
