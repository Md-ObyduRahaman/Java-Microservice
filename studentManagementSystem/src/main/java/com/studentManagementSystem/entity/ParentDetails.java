package com.studentManagementSystem.entity;

import lombok.Data;

@Data
public class ParentDetails {

    private Long id;

    private String fatherName;
    private String motherName;
    private String phoneNumber;
    private String email;
    private String address;

    private Integer studentId;


}
