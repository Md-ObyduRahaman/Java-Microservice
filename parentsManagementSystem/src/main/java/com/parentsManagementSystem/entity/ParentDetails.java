package com.parentsManagementSystem.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "parent_details")
@NoArgsConstructor
@Data
public class ParentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fatherName;
    private String motherName;
    private String phoneNumber;
    private String email;
    private String address;

    private Integer studentId;


}
