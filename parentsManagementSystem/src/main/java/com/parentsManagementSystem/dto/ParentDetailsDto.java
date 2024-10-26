package com.parentsManagementSystem.dto;


import com.parentsManagementSystem.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;


@Data
public class ParentDetailsDto{

    private String fatherName;
    private String motherName;
    private String phoneNumber;
    private String email;
    private String address;
    private Integer studentId;


}
