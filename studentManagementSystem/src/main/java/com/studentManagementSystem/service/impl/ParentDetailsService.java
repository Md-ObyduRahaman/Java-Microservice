package com.studentManagementSystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentManagementSystem.dto.ParentDetailsDto;

import com.studentManagementSystem.exception.GlobalExceptionHandler;
import com.studentManagementSystem.exception.ResourceNotFoundException;
import com.studentManagementSystem.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
public class ParentDetailsService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ParentService parentService;
    public Optional<ParentDetailsDto> getParentById(Integer id) {
        String response;
        try {
             response = new ObjectMapper().writeValueAsString(parentService.getParentDetails(id));
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Parents data not found of this student");

        }

        Map map = null;
        try {
            map = objectMapper.readValue(response, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ParentDetailsDto parentDetailsDto = objectMapper.convertValue(map, ParentDetailsDto.class);
        return Optional.ofNullable(parentDetailsDto);

    }
    public Optional<ParentDetailsDto> addParent(ParentDetailsDto parentDetails) {
        String response;
        try {
             response = new ObjectMapper().writeValueAsString(parentService.addParent(parentDetails));
        }
        catch (ResourceNotFoundException | JsonProcessingException e){
            throw new ResourceNotFoundException("Parents data not found of this student");
        }


        Map map = null;
        try {
            map = objectMapper.readValue(response, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        ParentDetailsDto addParentDetailsDto = objectMapper.convertValue(map.get("data"), ParentDetailsDto.class);
        return Optional.ofNullable(addParentDetailsDto);

    }
}
