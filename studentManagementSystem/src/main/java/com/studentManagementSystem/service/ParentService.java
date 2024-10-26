package com.studentManagementSystem.service;


import com.studentManagementSystem.entity.ApiBaseResponse ;
import com.studentManagementSystem.entity. BaseResponse;
import com.studentManagementSystem.entity.ParentDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@FeignClient(name = "PARENT-MANAGEMENT-SYSTEM")
public interface ParentService {
    @GetMapping("/Parent/guardian/{parentID}")
    public ApiBaseResponse <ParentDetails> getParentDetails(@PathVariable("parentID") Integer parentID);
    @PostMapping("/Parent/addParentDetails")
    public ApiBaseResponse <ParentDetails> addParent(@RequestBody ParentDetails Parent);


}