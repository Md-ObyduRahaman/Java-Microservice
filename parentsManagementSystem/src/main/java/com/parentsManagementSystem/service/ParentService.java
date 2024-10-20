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



    public Optional<List<ParentDetails>> getAllParentDetailss() {
        List<ParentDetails> ParentDetailss = parentRepository.findAll();
        return ParentDetailss.isEmpty() ? Optional.empty() : Optional.of(ParentDetailss);
    }

    public Optional<ParentDetails> getParentDetailsById(int id) {
        return parentRepository.findById(id);
    }

    public Optional<ParentDetails> addParentDetails(ParentDetails ParentDetails) {
        parentRepository.save(ParentDetails);
        return Optional.of(ParentDetails);
    }

    public Optional<ParentDetails> updateParentDetails(ParentDetails ParentDetails) {
        parentRepository.save(ParentDetails);
        return Optional.of(ParentDetails);
    }
    public Optional<ParentDetails> deleteParentDetails(Integer ParentDetailsId) {
        Optional<ParentDetails> ParentDetails = parentRepository.findById(ParentDetailsId);
        ParentDetails.ifPresent(parentRepository::delete); // Delete if ParentDetails is present
        return ParentDetails; // Return the ParentDetails if found, or Optional.empty() if not found
    }

}
