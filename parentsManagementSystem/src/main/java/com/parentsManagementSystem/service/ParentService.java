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



    public Optional<List<ParentDetails>> getAllParents() {
        List<ParentDetails> Parents = parentRepository.findAll();
        return Parents.isEmpty() ? Optional.empty() : Optional.of(Parents);
    }

    public Optional<ParentDetails> getParentById(int id) {
        return parentRepository.findById(id);
    }

    public Optional<ParentDetails> getParentByStudentId(int id) {
        return parentRepository.findByStudentId(id);
    }

    public Optional<ParentDetails> addParent(ParentDetails Parent) {
        parentRepository.save(Parent);
        return Optional.of(Parent);
    }

    public Optional<ParentDetails> updateParent(ParentDetails Parent) {
        parentRepository.save(Parent);
        return Optional.of(Parent);
    }
    public Optional<ParentDetails> deleteParent(Integer ParentId) {
        Optional<ParentDetails> Parent = parentRepository.findById(ParentId);
        Parent.ifPresent(parentRepository::delete); // Delete if Parent is present
        return Parent; // Return the Parent if found, or Optional.empty() if not found
    }

}
