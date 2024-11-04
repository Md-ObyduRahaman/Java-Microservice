package com.studentManagementSystem.service;

import com.studentManagementSystem.dto.StudentDto;
import com.studentManagementSystem.entity.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {


    Optional<List<Student>> getAllStudents();

    Optional<Student> getStudentById(int id);

    StudentDto addStudent(StudentDto student);

    Optional<Student> updateStudent(Student student);

    boolean deleteStudent(Integer studentId);
}
