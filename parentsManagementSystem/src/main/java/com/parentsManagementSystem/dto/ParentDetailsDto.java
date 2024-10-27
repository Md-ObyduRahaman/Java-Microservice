package com.parentsManagementSystem.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
@Schema(name = "Parent Details",
        description = "Details of the parent such as name, phone number, email and address")
public class ParentDetailsDto{

    @NotEmpty(message = "Father name should not be empty")
    @Size(min = 3, max = 30, message = "Father name must be between 3 and 30 characters")
    private String fatherName;

    @NotEmpty(message = "Mother name should not be empty")
    @Size(min = 3, max = 30, message = "Mother name must be between 3 and 30 characters")
    private String motherName;

    @NotEmpty(message = "Phone number should not be empty")
    @Size(min = 11, max = 11, message = "Phone number must be 11 digits")
    private String phoneNumber;

    @NotEmpty(message = "Email should not be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "Email should be in @gmail.com format")
    private String email;


    @NotEmpty(message = "Address should not be empty")
    @Size(min = 3, max = 30, message = "Address must be between 3 and 30 characters")
    private String address;


    private Integer studentId;


}
