package com.ParentDetailsManagementSystem.controller;

import com.ParentDetailsManagementSystem.entity.BaseResponse;
import com.ParentDetailsManagementSystem.entity.ParentDetails;
import com.ParentDetailsManagementSystem.exception.ResourceNotFoundException;
import com.ParentDetailsManagementSystem.service.ParentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ParentDetails")
public class ParentDetailsRegistration {


    @Autowired
    private ParentDetailsService ParentDetailsService;

    @GetMapping("/allParentDetailss")
    public ResponseEntity<BaseResponse<List<ParentDetails>>> getParentDetailsDetails(){
        BaseResponse<List<ParentDetails>> response;
        Optional<List<ParentDetails>> ParentDetailss = ParentDetailsService.getAllParentDetailss();
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
        Optional<ParentDetails> ParentDetails = ParentDetailsService.getParentDetailsById(ParentDetailsId);
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
        Optional<ParentDetails> ParentDetails1=ParentDetailsService.addParentDetails(ParentDetails);

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
        Optional<ParentDetails> ParentDetails1=ParentDetailsService.deleteParentDetails(ParentDetailsID);
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
