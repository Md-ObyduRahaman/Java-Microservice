package com.parentsManagementSystem.mapper;

import com.parentsManagementSystem.dto.ParentDetailsDto;
import com.parentsManagementSystem.entity.ParentDetails;

public class MapperParentDetailsMapper {

    public static ParentDetailsDto mapToParentDetailsDto(ParentDetails parentDetails, ParentDetailsDto parentDetailsDto) {

        parentDetailsDto.setFatherName(parentDetails.getFatherName());
        parentDetailsDto.setMotherName(parentDetails.getMotherName());
        parentDetailsDto.setPhoneNumber(parentDetails.getPhoneNumber());
        parentDetailsDto.setEmail(parentDetails.getEmail());
        parentDetailsDto.setAddress(parentDetails.getAddress());
        parentDetailsDto.setStudentId(parentDetails.getStudentId());
        return parentDetailsDto;
    }

    public static ParentDetails mapToParentDetails(ParentDetailsDto parentDetailsDto, ParentDetails parentDetails) {
        parentDetails.setFatherName(parentDetailsDto.getFatherName());
        parentDetails.setMotherName(parentDetailsDto.getMotherName());
        parentDetails.setPhoneNumber(parentDetailsDto.getPhoneNumber());
        parentDetails.setEmail(parentDetailsDto.getEmail());
        parentDetails.setAddress(parentDetailsDto.getAddress());
        parentDetails.setStudentId(parentDetailsDto.getStudentId());
        return parentDetails;
    }
}
