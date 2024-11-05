package com.studentManagementSystem.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentManagementSystem.exception.ParentServiceException;
import feign.FeignException;

import java.util.Map;

public class FeignErrorHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Method to parse JSON response into Map
    public static Map<String, Object> parseResponse(Object response) {
        try {
            String jsonResponse = objectMapper.writeValueAsString(response);
            return objectMapper.readValue(jsonResponse, Map.class);
        }
        catch (JsonProcessingException e) {
            throw new ParentServiceException("JSON_PROCESSING_ERROR", "Error processing JSON response", null, null);
        }

    }

    // Method to handle FeignException and throw ParentServiceException
    public static void handleFeignException(FeignException feignEx) {
        try {
            Map<String, Object> map = objectMapper.readValue(feignEx.contentUTF8(), Map.class);

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
}
