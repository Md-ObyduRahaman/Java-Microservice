package com.parentsManagementSystem.controller;


import com.parentsManagementSystem.entity.BaseResponse;
import com.parentsManagementSystem.entity.ParentDetails;
import com.parentsManagementSystem.exception.ResourceNotFoundException;
import com.parentsManagementSystem.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Parent")
public class ParentsRegistration {


    @Autowired
    private ParentService parentService;

    @GetMapping("/allStudent")
    public ResponseEntity<BaseResponse<List<ParentDetails>>> getStudentDetails(){
        BaseResponse<List<ParentDetails>> response;
        Optional<List<ParentDetails>> Students = parentService.getAllStudents();
        if (Students.isPresent()) {
            List<ParentDetails> StudentList = Students.get();
            response = new BaseResponse<>(
                    "Resource found successfully", StudentList, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Resource not found" );
        }
    }

    @GetMapping("{StudentId}")
    public ResponseEntity<BaseResponse<Optional<ParentDetails>>> getStudentDetails(@PathVariable Integer StudentId){
        BaseResponse<Optional<ParentDetails>> response;
        Optional<ParentDetails> Student = parentService.getStudentById(StudentId);
        if (Student.isPresent()) {
            response = new BaseResponse<>(
                    "Resource found successfully", Student, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Resource not found" );
        }
    }

    @PostMapping("addStudent")
    public ResponseEntity<BaseResponse<ParentDetails>> addStudent(@RequestBody ParentDetails Student){
        BaseResponse<ParentDetails> response;
        Optional<ParentDetails> Student1=parentService.addStudent(Student);

        if (Student1.isPresent()) {
            response = new BaseResponse<>(
                    "Student saved successfully", Student, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("Student not saved" );
        }
    }
    @DeleteMapping("{StudentID}")
    public ResponseEntity<BaseResponse<Boolean>> deleteStudent(@PathVariable Integer StudentID){
        Optional<ParentDetails> Student1=parentService.deleteStudent(StudentID);
        BaseResponse<Boolean> response;
        if (Student1.isPresent()) {
            response = new BaseResponse<>(
                    "Student Deleted successfully", true, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);}
        else {
            response = new BaseResponse<>(
                    "Student not found", false, HttpStatus.NOT_FOUND, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }



}
