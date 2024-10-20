package com.parentsManagementSystem.service;


import com.parentsManagementSystem.entity.ParentDetails;
import com.parentsManagementSystem.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParentService {

    @Autowired
    ParentRepository parentRepository;



    public Optional<List<ParentDetails>> getAllStudents() {
        List<ParentDetails> Students = parentRepository.findAll();
        return Students.isEmpty() ? Optional.empty() : Optional.of(Students);
    }

    public Optional<ParentDetails> getStudentById(int id) {
        return parentRepository.findById(id);
    }

    public Optional<ParentDetails> addStudent(ParentDetails Student) {
        parentRepository.save(Student);
        return Optional.of(Student);
    }

    public Optional<ParentDetails> updateStudent(ParentDetails Student) {
        parentRepository.save(Student);
        return Optional.of(Student);
    }
    public Optional<ParentDetails> deleteStudent(Integer StudentId) {
        Optional<ParentDetails> Student = parentRepository.findById(StudentId);
        Student.ifPresent(parentRepository::delete); // Delete if Student is present
        return Student; // Return the Student if found, or Optional.empty() if not found
    }

}
