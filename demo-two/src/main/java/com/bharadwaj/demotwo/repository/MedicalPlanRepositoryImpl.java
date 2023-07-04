package com.bharadwaj.demotwo.repository;

import com.bharadwaj.demotwo.model.Plan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class MedicalPlanRepositoryImpl implements MedicalPlanRepository{

    private final RedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    @Autowired
    public MedicalPlanRepositoryImpl(RedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    private static final String KEY="MEDICAL_PLAN";
    @Override
    public boolean savePlan(Plan p) {
        try{
            redisTemplate.opsForHash().put(KEY, p.getObjectId().toString(), p);
            return true;
        }catch(Exception e){
            log.error("Error Saving plan : ", e);
            return false;
        }
    }

    @Override
    public List<Plan> fetchAllPlans() {
        List<Plan> plans;
        plans = redisTemplate.opsForHash().values(KEY);
        return plans;
    }

    @Override
    public Optional<Plan> getPlanById(String objectId) {
        Plan medicalPlan;
        medicalPlan = (Plan) redisTemplate.opsForHash().get(KEY, objectId.toString());
        return Optional.ofNullable(medicalPlan);
    }

    @Override
    public Optional<Boolean>  deletePlan(String objectId) {
        try{
            if(redisTemplate.opsForHash().hasKey(KEY, objectId.toString())){
                redisTemplate.opsForHash().delete(KEY, objectId.toString());
                return Optional.of(true);
            }else {
                return Optional.empty();
            }
        }catch(Exception e){
            log.error("Error deleting plan", e);
            return Optional.of(false);
        }
    }

    @Override
    public Optional<Boolean> updatePlanById(String objectId, Plan p) {
        try{
            if(redisTemplate.opsForHash().hasKey(KEY, objectId.toString())){
                redisTemplate.opsForHash().put(KEY, p.getObjectId().toString(), p);
                return Optional.of(true);
            }else {
                return Optional.empty();
            }
        }catch(Exception e){
            log.error("Error deleting plan", e);
            return Optional.of(false);
        }
    }

    @Override
    public Optional<Plan> patchPlan(String objectId, ObjectNode updates) throws IOException {
        Plan medicalPlan = (Plan) redisTemplate.opsForHash().get(KEY, objectId.toString());
        if (medicalPlan == null) {
            return Optional.empty();
        }

        JsonNode existingPlanNode = this.objectMapper.valueToTree(medicalPlan);
        JsonNode patched = this.objectMapper.readerForUpdating(existingPlanNode).readValue(updates);
        Plan patchedPlan = this.objectMapper.treeToValue(patched, Plan.class);
        redisTemplate.opsForHash().put(KEY, objectId, patchedPlan);
        return Optional.ofNullable(patchedPlan);
    }

}
