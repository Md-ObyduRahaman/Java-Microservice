package com.studentManagementSystem.service.impl;

import com.studentManagementSystem.dto.StudentDto;
import com.studentManagementSystem.entity.Student;
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
        return students.isEmpty() ? Optional.empty() : Optional.of(students);
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Optional<StudentDto> addStudent(StudentDto studentDto) {
        if (studentRepository.existsByEmail(studentDto.getEmail())) {
            throw new StudentAlreadyExistsException("Student with email '" + studentDto.getEmail() + "' already exists.");
        }

        Student student = StudentMapper.toEntity(studentDto);

        Student savedStudent = studentRepository.save(student);

        if (savedStudent.getId() == null) {
            throw new StudentSaveFailedException("Failed to save the student to the database.");
        }
        return Optional.of(StudentMapper.toDto(savedStudent));
    }

    public Optional<Student> updateStudent(Student student) {
        studentRepository.save(student);
        return Optional.of(student);
    }
    public Optional<Student> deleteStudent(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        student.ifPresent(studentRepository::delete);
        return student;
    }

}