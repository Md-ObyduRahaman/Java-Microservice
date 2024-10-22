package com.studentManagementSystem.service;


import com.studentManagementSystem.entity.BaseResponse;
import com.studentManagementSystem.entity.ParentDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(name = "PARENT-MANAGEMENT-SYSTEM")
public interface ParentService {
    @GetMapping("/Parent/{parentID}")
   // public BaseResponse<Optional<ParentDetails>> getParentDetails(@PathVariable("parentID") Integer parentID);
    public Object getParentByIds(@PathVariable("parentID") Integer parentID);
}
