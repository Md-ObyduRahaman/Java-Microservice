package com.studentManagementSystem.controller;


import com.studentManagementSystem.entity.ParentDetails;
import com.studentManagementSystem.entity.Student;
import com.studentManagementSystem.exception.ResourceNotFoundException;
import com.studentManagementSystem.service.ParentDetailsService;
import com.studentManagementSystem.service.StudentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
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
    public ResponseEntity<List<Student>> getStudentDetails(){
         List<Student> response;
        Optional<List<Student>> students = studentService.getAllStudents();
       return null;
    }

    @GetMapping("{studentId}")
    public ResponseEntity< Optional<Student>> getStudentDetails(@PathVariable Integer studentId){
         Optional<Student> response;
        Optional<Student> student = studentService.getStudentById(studentId);
        return null;
    }

    @PostMapping("addStudent")
    public ResponseEntity<Student> addStudent(@RequestBody Student student){
         Student response;
        Optional<Student> student1=studentService.addStudent(student);

        return null;
    }
    @DeleteMapping("{studentID}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable Integer studentID){
        Optional<Student> student1=studentService.deleteStudent(studentID);
         Boolean response;
        return null;

    }

    @GetMapping("guardian/{studentId}")
    //@CircuitBreaker(name = "parentDetailsBreaker" , fallbackMethod = "parentDetailsFallback")
    @Retry(name = "parentDetailsService", fallbackMethod = "parentDetailsFallback")
    public ResponseEntity< Optional<Student>> getStudentParentDetails(@PathVariable Integer studentId){
         Optional<Student> response;
        Optional<Student> student = studentService.getStudentById(studentId);
        return null;
    }


    public ResponseEntity< Optional<Student>> parentDetailsFallback(Integer studentId, Throwable throwable) {
         Optional<Student> response;
        // Fetch the student even if parentDetailsService fails
        Optional<Student> student = studentService.getStudentById(studentId);
        student.get().setParentDetails(null);

        return null;
    }


    @PostMapping("/addParent")
    public ResponseEntity< ParentDetails> addParent(@RequestBody ParentDetails Parent){
         ParentDetails response;
        Optional<ParentDetails> Parent1=parentDetailsService.addParent(Parent);

        return null;

    }

}