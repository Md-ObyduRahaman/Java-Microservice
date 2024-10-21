package com.studentManagementSystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentManagementSystem.entity.ParentDetails;
import com.studentManagementSystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
public class ParentDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public Optional<ParentDetails> getParentById(Integer id) {
        String response;
        try {
             response = restTemplate.getForObject("http://localhost:8083/Parent/" + id, String.class);
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Parents data not found of this student");

        }

        Map<String, Object> map = null;
        try {
            map = objectMapper.readValue(response, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ParentDetails parentDetails = objectMapper.convertValue(map.get("data"), ParentDetails.class);
        return Optional.ofNullable(parentDetails);

    }
}
