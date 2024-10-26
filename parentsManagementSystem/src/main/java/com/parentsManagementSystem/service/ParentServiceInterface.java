package com.parentsManagementSystem.service;

import com.parentsManagementSystem.dto.ParentDetailsDto;
import com.parentsManagementSystem.entity.ParentDetails;

import java.util.List;
import java.util.Optional;

public interface ParentServiceInterface {

List<ParentDetailsDto> getAllParents();

Optional<ParentDetailsDto> getParentById(int id);

Optional<ParentDetailsDto> getParentByStudentId(int id);

void addParent(ParentDetailsDto parentDetailsDto);

Optional<ParentDetailsDto> updateParent(ParentDetails parentDetails);

void deleteByParentId(Integer parentId);

}
