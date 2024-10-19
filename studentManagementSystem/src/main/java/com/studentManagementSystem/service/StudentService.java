package com.studentManagementSystem.service;

import com.studentManagementSystem.entity.Student;
import com.studentManagementSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;



    public Optional<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.isEmpty() ? Optional.empty() : Optional.of(students);
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> addStudent(Student student) {
        studentRepository.save(student);
        return Optional.of(student);
    }

    public Optional<Student> updateStudent(Student student) {
        studentRepository.save(student);
        return Optional.of(student);
    }
    public Optional<Student> deleteStudent(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        student.ifPresent(studentRepository::delete); // Delete if student is present
        return student; // Return the student if found, or Optional.empty() if not found
    }

}
