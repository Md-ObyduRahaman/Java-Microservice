package com.studentManagementSystem.service;


import com.studentManagementSystem.dto.ParentDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@FeignClient(name = "PARENT-MANAGEMENT-SYSTEM")
public interface ParentService {
    @GetMapping("/Parent/guardian/{studentId}")
    public ParentDetailsDto getParentDetails(@PathVariable("studentId") Integer parentID);
    @PostMapping("/Parent/addParentDetails")
    public ParentDetailsDto addParent(@RequestBody ParentDetailsDto Parent);


}