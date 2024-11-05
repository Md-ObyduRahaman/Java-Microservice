package com.studentManagementSystem.controller;


import com.parentsManagementSystem.dto.ResponseDto;
import com.studentManagementSystem.constants.StudentMangementConstants;
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

import static org.springframework.http.ResponseEntity.status;

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
        Optional<List<Student>> students = studentService.getAllStudents();
       return status(HttpStatus.OK).body(students.get());
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Optional<Student>> getStudentDetails(@PathVariable Integer studentId){
        Optional<Student> student = studentService.getStudentById(studentId);
        return status(HttpStatus.OK)
                .body(student);
    }

    @PostMapping("addStudent")
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto student){
        StudentDto studentDto = iStudentService.addStudent(student);
        return status(HttpStatus.CREATED)
                .body(studentDto);
    }
    @DeleteMapping("{studentID}")
    public ResponseEntity<ResponseDto> deleteStudent(@PathVariable Integer studentID){
        boolean deleteStudent = iStudentService.deleteStudent(studentID);
        if(deleteStudent) {
            return status(HttpStatus.OK)
                    .body(new ResponseDto(StudentMangementConstants.STATUS_200, StudentMangementConstants.MESSAGE_200));
        }
        else {
            return status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(StudentMangementConstants.STATUS_417, StudentMangementConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("guardian/{studentId}")
    //@CircuitBreaker(name = "parentDetailsBreaker" , fallbackMethod = "parentDetailsFallback")
    @Retry(name = "parentDetailsService", fallbackMethod = "parentDetailsFallback")
    public ResponseEntity< Optional<ParentDetailsDto>> getStudentParentDetails(@PathVariable Integer studentId){
         Optional<ParentDetailsDto> response;
        Optional<ParentDetailsDto> student = parentDetailsService.getParentById(studentId);
        return status(HttpStatus.OK)
                .body(student);
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