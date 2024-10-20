package com.parentsManagementSystem.controller;


import com.parentsManagementSystem.entity.BaseResponse;
import com.parentsManagementSystem.entity.ParentDetails;
import com.parentsManagementSystem.exception.ResourceNotFoundException;
import com.parentsManagementSystem.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Parent")
public class ParentsRegistration {


    @Autowired
    private ParentService parentService;

    @GetMapping("/allParentDetails")
    public ResponseEntity<BaseResponse<List<ParentDetails>>> getParentDetailsDetails(){
        BaseResponse<List<ParentDetails>> response;
        Optional<List<ParentDetails>> ParentDetailss = parentService.getAllParentDetailss();
        if (ParentDetailss.isPresent()) {
            List<ParentDetails> ParentDetailsList = ParentDetailss.get();
            response = new BaseResponse<>(
                    "Resource found successfully", ParentDetailsList, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Resource not found" );
        }
    }

    @GetMapping("{ParentDetailsId}")
    public ResponseEntity<BaseResponse<Optional<ParentDetails>>> getParentDetailsDetails(@PathVariable Integer ParentDetailsId){
        BaseResponse<Optional<ParentDetails>> response;
        Optional<ParentDetails> ParentDetails = parentService.getParentDetailsById(ParentDetailsId);
        if (ParentDetails.isPresent()) {
            response = new BaseResponse<>(
                    "Resource found successfully", ParentDetails, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Resource not found" );
        }
    }

    @PostMapping("addParentDetails")
    public ResponseEntity<BaseResponse<ParentDetails>> addParentDetails(@RequestBody ParentDetails ParentDetails){
        BaseResponse<ParentDetails> response;
        Optional<ParentDetails> ParentDetails1=parentService.addParentDetails(ParentDetails);

        if (ParentDetails1.isPresent()) {
            response = new BaseResponse<>(
                    "ParentDetails saved successfully", ParentDetails, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("ParentDetails not saved" );
        }
    }
    @DeleteMapping("{ParentDetailsID}")
    public ResponseEntity<BaseResponse<Boolean>> deleteParentDetails(@PathVariable Integer ParentDetailsID){
        Optional<ParentDetails> ParentDetails1=parentService.deleteParentDetails(ParentDetailsID);
        BaseResponse<Boolean> response;
        if (ParentDetails1.isPresent()) {
            response = new BaseResponse<>(
                    "ParentDetails Deleted successfully", true, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);}
        else {
            response = new BaseResponse<>(
                    "ParentDetails not found", false, HttpStatus.NOT_FOUND, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }



}
