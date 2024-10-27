package com.parentsManagementSystem.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Entity
@Table(name = "parent_details")
@Data
public class ParentDetails extends  BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private String fatherName;


    private String motherName;


    private String phoneNumber;

    private String email;

    private String address;


    @Column(unique = true)
    private Integer studentId;


}
