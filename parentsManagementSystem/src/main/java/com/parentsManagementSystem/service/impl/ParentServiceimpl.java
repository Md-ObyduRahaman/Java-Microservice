package com.parentsManagementSystem.service.impl;


import com.parentsManagementSystem.dto.ParentDetailsDto;
import com.parentsManagementSystem.entity.ParentDetails;
import com.parentsManagementSystem.exception.CustomerAlreadyExistsException;
import com.parentsManagementSystem.exception.ResourceNotFoundException;
import com.parentsManagementSystem.mapper.MapperParentDetailsMapper;
import com.parentsManagementSystem.repository.ParentRepository;
import com.parentsManagementSystem.service.ParentServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ParentServiceimpl implements ParentServiceInterface {

    @Autowired
    ParentRepository parentRepository;


    public List<ParentDetailsDto> getAllParents() {
        List<ParentDetails> parents = parentRepository.findAll();
        if (parents.isEmpty()) {
            throw new ResourceNotFoundException("Parents", "forAllParents","all");
        }
        List<ParentDetailsDto> parentsDto = new ArrayList<>();
        for (ParentDetails parent : parents) {
            ParentDetailsDto parentDto = MapperParentDetailsMapper.mapToParentDetailsDto(parent, new ParentDetailsDto());
            parentsDto.add(parentDto);
        }
        return parentsDto;
    }

    public Optional<ParentDetailsDto> getParentById(int id) {
        Optional<ParentDetails> parent = parentRepository.findById(id);
        if (parent.isPresent()) {
            return Optional.of(MapperParentDetailsMapper.mapToParentDetailsDto(parent.get(), new ParentDetailsDto()));
        } else {
            throw new ResourceNotFoundException("parent", "parentId",parent.get().getStudentId().toString());
        }
    }

    public Optional<ParentDetailsDto> getParentByStudentId(int id) {
        Optional<ParentDetails> parent = parentRepository.findByStudentId(id);
        if (parent.isPresent()) {
            return Optional.of(MapperParentDetailsMapper.mapToParentDetailsDto(parent.get(), new ParentDetailsDto()));
        } else {
            throw new ResourceNotFoundException("parent", "parentId",parent.get().getStudentId().toString());
        }
    }

    @Override
    public void addParent( ParentDetailsDto parentDetailsDto) {
        Optional<ParentDetails> optionalParent = parentRepository.findByStudentId(parentDetailsDto.getStudentId());
        if (optionalParent.isPresent()) {
            throw new CustomerAlreadyExistsException("Parent Already Exists with Student Id: "
                    + optionalParent.get().getStudentId());
        }
         Optional.of(MapperParentDetailsMapper.mapToParentDetailsDto(parentRepository.save(MapperParentDetailsMapper.mapToParentDetails(parentDetailsDto, new ParentDetails())), new ParentDetailsDto()));
    }

    @Override
    public boolean updateParent(ParentDetailsDto parentDetailsDto) {
        boolean isUpdate=false;
        Optional<ParentDetails> parent = parentRepository.findById(parentDetailsDto.getStudentId());
        if (parent.isPresent()) {
            parentRepository.save(MapperParentDetailsMapper.mapToParentDetails(parentDetailsDto, parent.get()));
            isUpdate=true;
            return isUpdate;
        } else {
            throw new ResourceNotFoundException("parent", "parentId",parentDetailsDto.getStudentId().toString());
        }
    }

    public boolean deleteByParentId(Integer ParentId) {
        Optional<ParentDetails> parent = parentRepository.findById(ParentId);
        if (parent.isPresent()) {
            parentRepository.deleteById(ParentId);
            return true;
        } else {
            throw new ResourceNotFoundException("parent", "parentId",parent.get().getStudentId().toString());
        }
    }


}
