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
    public ResponseEntity<BaseResponse<List<ParentDetails>>> getParentDetails(){
        BaseResponse<List<ParentDetails>> response;
        Optional<List<ParentDetails>> Parents = parentService.getAllParents();
        if (Parents.isPresent()) {
            List<ParentDetails> ParentList = Parents.get();
            response = new BaseResponse<>(
                    "Resource found successfully", ParentList, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new ResourceNotFoundException("Resource not found" );
        }
    }

    @GetMapping("{parentId}")
    public ResponseEntity<BaseResponse<ParentDetails>> getParentDetails(@PathVariable Integer parentId){
        BaseResponse<ParentDetails> response;
        Optional<ParentDetails> Parent = parentService.getParentById(parentId);
        if (Parent.isPresent()) {
            response = new BaseResponse<>(
                    "Resource found successfully", Parent.get(), HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Resource not found" );
        }
    }
    @GetMapping("guardian/{studentId}")
    public ResponseEntity<BaseResponse<ParentDetails>> getparentDeatailsWithParentId(@PathVariable Integer studentId){
        BaseResponse<ParentDetails> response;
        Optional<ParentDetails> Parent = parentService.getParentByStudentId(studentId);
        if (Parent.isPresent()) {
            response = new BaseResponse<>(
                    "Resource found successfully", Parent.get(), HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            throw new ResourceNotFoundException("Resource not found" );
        }
    }

    @PostMapping("addParentDetails")
    public ResponseEntity<BaseResponse<ParentDetails>> addParent(@RequestBody ParentDetails Parent){
        BaseResponse<ParentDetails> response;
        Optional<ParentDetails> Parent1=parentService.addParent(Parent);

        if (Parent1.isPresent()) {
            response = new BaseResponse<>(
                    "Parent saved successfully", Parent, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            throw new ResourceNotFoundException("Parent not saved" );
        }
    }
    @DeleteMapping("{parentID}")
    public ResponseEntity<BaseResponse<Boolean>> deleteParent(@PathVariable Integer parentID){
        Optional<ParentDetails> Parent1=parentService.deleteParent(parentID);
        BaseResponse<Boolean> response;
        if (Parent1.isPresent()) {
            response = new BaseResponse<>(
                    "Parent Deleted successfully", true, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);}
        else {
            response = new BaseResponse<>(
                    "Parent not found", false, HttpStatus.NOT_FOUND, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }



}
