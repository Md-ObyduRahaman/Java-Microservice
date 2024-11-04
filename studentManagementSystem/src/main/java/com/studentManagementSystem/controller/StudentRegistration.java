package com.studentManagementSystem.controller;


import com.parentsManagementSystem.dto.ResponseDto;
import com.studentManagementSystem.dto.ParentDetailsDto;
import com.studentManagementSystem.dto.StudentDto;
import com.studentManagementSystem.entity.Student;
import com.studentManagementSystem.service.IStudentService;
import com.studentManagementSystem.service.impl.ParentDetailsService;
import com.studentManagementSystem.service.impl.StudentService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentRegistration {


    @Autowired
    private StudentService studentService;

    @Autowired
    ParentDetailsService parentDetailsService;

   
   @Autowired
    IStudentService iStudentService;

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
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto student){
        StudentDto studentDto = iStudentService.addStudent(student);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentDto);
    }
    @DeleteMapping("{studentID}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable Integer studentID){
        Optional<Student> student1=iStudentService.deleteStudent(studentID);
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
    public ResponseEntity<ParentDetailsDto> addParent(@RequestBody ParentDetailsDto Parent){
         ParentDetailsDto response;
        Optional<ParentDetailsDto> Parent1=parentDetailsService.addParent(Parent);

        return null;

    }

}