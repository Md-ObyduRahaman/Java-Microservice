package com.studentManagementSystem.service;


import com.studentManagementSystem.entity.ApiBaseResponse;
import com.studentManagementSystem.entity.BaseResponse;
import com.studentManagementSystem.entity.ParentDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@FeignClient(name = "PARENT-MANAGEMENT-SYSTEM")
public interface ParentService {
    @GetMapping("/Parent/guardian/{parentID}")
      public ApiBaseResponse<ParentDetails> getParentDetails(@PathVariable("parentID") Integer parentID);
   // public Object getParentDetails(@PathVariable("parentID") Integer parentID);
}
