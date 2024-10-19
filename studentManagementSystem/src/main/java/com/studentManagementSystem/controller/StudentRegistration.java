package com.studentManagementSystem.controller;

import com.studentManagementSystem.entity.BaseResponse;
import com.studentManagementSystem.entity.Student;
import com.studentManagementSystem.exception.ResourceNotFoundException;
import com.studentManagementSystem.repository.StudentRepository;
import com.studentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentRegistration {


    @Autowired
    private StudentService studentService;

    @GetMapping("/allStudents")
    public ResponseEntity<BaseResponse<List<Student>>> getStudentDetails(){
        BaseResponse<List<Student>> response;
        Optional<List<Student>> students = studentService.getAllStudents();
        if (students.isPresent()) {
            List<Student> studentList = students.get();
            response = new BaseResponse<>(
                    "Resource found successfully", studentList, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Resource not found" );
        }
    }

    @GetMapping("{studentId}")
    public ResponseEntity<BaseResponse<Optional<Student>>> getStudentDetails(@PathVariable Integer studentId){
        BaseResponse<Optional<Student>> response;
        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isPresent()) {
            response = new BaseResponse<>(
                    "Resource found successfully", student, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Resource not found" );
        }
    }

   /* {
        "id": 1,
            "firstName": "Md Oydur",
            "lastName": "sojin",
            "email": "so@gmail.com",
            "dateOfBirth": "2024-09-02",
            "phoneNumber": "01783726898",
            "address": "panthpath, Dahaka",
            "gender": "M"
    }*/


}
