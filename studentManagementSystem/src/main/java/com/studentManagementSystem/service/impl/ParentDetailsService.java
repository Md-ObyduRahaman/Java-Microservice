package com.studentManagementSystem.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentManagementSystem.dto.ParentDetailsDto;

import com.studentManagementSystem.exception.GlobalExceptionHandler;
import com.studentManagementSystem.exception.ParentServiceException;
import com.studentManagementSystem.exception.ResourceNotFoundException;
import com.studentManagementSystem.service.ParentService;
import com.studentManagementSystem.util.FeignErrorHandler;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

@Service
public class ParentDetailsService {

    @Autowired
    ParentService parentService;
    public Optional<ParentDetailsDto> getParentById(Integer id) {
        Map<String, Object> map = null;
        try {
            map = FeignErrorHandler.parseResponse(parentService.getParentDetails(id));

        }catch (FeignException feignEx) {
            FeignErrorHandler.handleFeignException(feignEx);
        }
        ParentDetailsDto addParentDetailsDto = new ObjectMapper().convertValue(map, ParentDetailsDto.class);
        return Optional.ofNullable(addParentDetailsDto);
    }
    public Optional<ParentDetailsDto> addParent(ParentDetailsDto parentDetails) {
        Map<String, Object> map = null;
        try {
            map = FeignErrorHandler.parseResponse(parentService.addParent(parentDetails));
        }
        catch (FeignException feignEx) {
            FeignErrorHandler.handleFeignException(feignEx);
        }
        ParentDetailsDto addParentDetailsDto = new ObjectMapper().convertValue(map, ParentDetailsDto.class);
        return Optional.ofNullable(addParentDetailsDto);
    }

}
