package com.studentManagementSystem.service.impl;

import com.studentManagementSystem.dto.StudentDto;
import com.studentManagementSystem.entity.Student;
import com.studentManagementSystem.exception.CustomerAlreadyExistsException;
import com.studentManagementSystem.exception.ResourceNotFoundException;
import com.studentManagementSystem.exception.StudentAlreadyExistsException;
import com.studentManagementSystem.exception.StudentSaveFailedException;
import com.studentManagementSystem.mapper.StudentMapper;
import com.studentManagementSystem.repository.StudentRepository;
import com.studentManagementSystem.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    @Autowired
    StudentRepository studentRepository;






    public Optional<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        if (students.isEmpty()) {
            throw new ResourceNotFoundException("No students found");
        } else {
            return Optional.of(students);
        }
    }

    public Optional<Student> getStudentById(int id) {

        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student;
        } else {
            throw new ResourceNotFoundException("Student with id " + id + " not found");
        }

    }

    public StudentDto addStudent(StudentDto studentDto) {
        // Check if a student with the same email already exists
        if (studentRepository.existsByEmail(studentDto.getEmail())) {
            throw new StudentAlreadyExistsException("Student with email '" + studentDto.getEmail() + "' already exists.");
        }

        // Convert DTO to entity
        Student student = StudentMapper.toEntity(studentDto);

        // Save the student entity to the repository
        Student savedStudent = studentRepository.save(student);

        // Check if the save was successful
        if (savedStudent.getId() == null) {
            throw new StudentSaveFailedException("Failed to save the student to the database.");
        }

        return StudentMapper.toDto(savedStudent);
    }


    public Optional<Student> updateStudent(Student student) {
        studentRepository.save(student);
        return Optional.of(student);
    }
    public boolean deleteStudent(Integer studentId) {

        Optional<Student> optionalParent = studentRepository.findById(studentId);
        boolean isDeleted = false;
        if (optionalParent.isPresent()) {
             isDeleted = studentRepository.existsById(studentId);
        }
        if (isDeleted) {
            studentRepository.deleteById(studentId);
            return true;
        } else {
            throw new ResourceNotFoundException("Student with ID '" + studentId + "' does not exist.");
        }
    }




}