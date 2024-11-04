package com.studentManagementSystem.mapper;

import com.studentManagementSystem.entity.Student;
import com.studentManagementSystem.dto.StudentDto;



public class StudentMapper {

    // Convert Student entity to StudentDto
    public static StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setEmail(student.getEmail());
        studentDto.setDateOfBirth(student.getDateOfBirth());
        studentDto.setPhoneNumber(student.getPhoneNumber());
        studentDto.setAddress(student.getAddress());
        studentDto.setGender(student.getGender());
        studentDto.setParentDetails(student.getParentDetails());
        return studentDto;
    }

    // Convert StudentDto to Student entity
    public static Student toEntity(StudentDto studentDto) {
        if (studentDto == null) {
            return null;
        }
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setAddress(studentDto.getAddress());
        student.setGender(studentDto.getGender());
        student.setParentDetails(studentDto.getParentDetails());
        return student;
    }

    // Update an existing Student entity with data from StudentDto
    public static void updateEntityFromDto(StudentDto studentDto, Student student) {
        if (studentDto == null || student == null) {
            return;
        }
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setAddress(studentDto.getAddress());
        student.setGender(studentDto.getGender());
        student.setParentDetails(studentDto.getParentDetails());
    }
}
