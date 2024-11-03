package com.studentManagementSystem.entity;


import com.studentManagementSystem.dto.ParentDetailsDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String gender;

    @Transient
    private ParentDetailsDto parentDetails;

    // Constructors
    public Student() {
    }

    public Student(String firstName, String lastName, String email, LocalDate dateOfBirth, String phoneNumber, String address, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
    }

}

