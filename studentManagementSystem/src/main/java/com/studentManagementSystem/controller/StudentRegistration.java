package com.studentManagementSystem.controller;

import com.studentManagementSystem.entity.BaseResponse;
import com.studentManagementSystem.entity.ParentDetails;
import com.studentManagementSystem.entity.Student;
import com.studentManagementSystem.exception.ResourceNotFoundException;
import com.studentManagementSystem.service.ParentDetailsService;
import com.studentManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentRegistration {


    @Autowired
    private StudentService studentService;

    @Autowired
    ParentDetailsService parentDetailsService;

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

    @PostMapping("addStudent")
    public ResponseEntity<BaseResponse<Student>> addStudent(@RequestBody Student student){
        BaseResponse<Student> response;
        Optional<Student> student1=studentService.addStudent(student);

        if (student1.isPresent()) {
            response = new BaseResponse<>(
                    "Student saved successfully", student, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("Student not saved" );
        }
    }
    @DeleteMapping("{studentID}")
    public ResponseEntity<BaseResponse<Boolean>> deleteStudent(@PathVariable Integer studentID){
        Optional<Student> student1=studentService.deleteStudent(studentID);
        BaseResponse<Boolean> response;
        if (student1.isPresent()) {
            response = new BaseResponse<>(
                    "Student Deleted successfully", true, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);}
        else {
            response = new BaseResponse<>(
                    "Student not found", false, HttpStatus.NOT_FOUND, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("guardian/{studentId}")
    public ResponseEntity<BaseResponse<Optional<Student>>> getStudentParentDetails(@PathVariable Integer studentId){
        BaseResponse<Optional<Student>> response;
        Optional<Student> student = studentService.getStudentById(studentId);



        if (student.isPresent()) {
            Optional<ParentDetails> parentDetails = parentDetailsService.getParentById(studentId);
            parentDetails.ifPresent(details -> student.get().setParentDetails(details));
            response = new BaseResponse<>(
                    "Student found successfully", student, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Student not found" );
        }
    }



}