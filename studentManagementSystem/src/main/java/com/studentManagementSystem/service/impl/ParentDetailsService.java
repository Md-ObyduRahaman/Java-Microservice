package com.studentManagementSystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentManagementSystem.dto.ParentDetailsDto;

import com.studentManagementSystem.exception.GlobalExceptionHandler;
import com.studentManagementSystem.exception.ParentServiceException;
import com.studentManagementSystem.exception.ResourceNotFoundException;
import com.studentManagementSystem.service.ParentService;
import feign.FeignException;
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
        String response = null;
        Map<String, Object> map = null;


        try {
            response = new ObjectMapper().writeValueAsString(parentService.getParentDetails(id));
            map = new ObjectMapper().readValue(response, Map.class);

        }  catch (JsonProcessingException e) {
            throw new ParentServiceException("JSON_PROCESSING_ERROR", "Error processing JSON response", null, null);
        }
        catch (FeignException feignEx) {
            try {
                map = new ObjectMapper().readValue(feignEx.contentUTF8(), Map.class);

                String apiPath = (String) map.get("apiPath");
                String errorCode = (String) map.get("errorCode");
                String errorMessage = (String) map.get("errorMessage");
                String errorTime = (String) map.get("errorTime");

                throw new ParentServiceException(errorCode, errorMessage, apiPath, errorTime);

            } catch (Exception parseEx) {
                if (parseEx instanceof  ParentServiceException) throw (ParentServiceException) parseEx;
                throw new ParentServiceException("FEIGN_ERROR", "Error parsing Feign exception response", null, null);
            }
        }

        ParentDetailsDto addParentDetailsDto = new ObjectMapper().convertValue(map, ParentDetailsDto.class);
        return Optional.ofNullable(addParentDetailsDto);

    }
    public Optional<ParentDetailsDto> addParent(ParentDetailsDto parentDetails) {
        String response = null;
        Map<String, Object> map = null;

        try {
            response = new ObjectMapper().writeValueAsString(parentService.addParent(parentDetails));
            map = new ObjectMapper().readValue(response, Map.class);

        }  catch (JsonProcessingException e) {
            throw new ParentServiceException("JSON_PROCESSING_ERROR", "Error processing JSON response", null, null);
        }
        catch (FeignException feignEx) {
            try {
               map = new ObjectMapper().readValue(feignEx.contentUTF8(), Map.class);

                String apiPath = (String) map.get("apiPath");
                String errorCode = (String) map.get("errorCode");
                String errorMessage = (String) map.get("errorMessage");
                String errorTime = (String) map.get("errorTime");

                throw new ParentServiceException(errorCode, errorMessage, apiPath, errorTime);

            } catch (Exception parseEx) {
                if (parseEx instanceof  ParentServiceException) throw (ParentServiceException) parseEx;
                throw new ParentServiceException("FEIGN_ERROR", "Error parsing Feign exception response", null, null);
            }
        }

        ParentDetailsDto addParentDetailsDto = new ObjectMapper().convertValue(map.get("data"), ParentDetailsDto.class);
        return Optional.ofNullable(addParentDetailsDto);
    }

}
