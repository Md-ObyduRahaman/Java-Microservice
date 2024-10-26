package com.parentsManagementSystem.controller;



import com.parentsManagementSystem.constants.ParentsConstants;
import com.parentsManagementSystem.dto.ParentDetailsDto;
import com.parentsManagementSystem.dto.ResponseDto;
import com.parentsManagementSystem.entity.ParentDetails;
import com.parentsManagementSystem.service.ParentServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Parent")
public class ParentsRegistration {

    private ParentServiceInterface parentService;

    public ParentsRegistration(ParentServiceInterface parentService) {
        this.parentService = parentService;
    }


    @GetMapping("/allParentDetails")
    public ResponseEntity< List<ParentDetailsDto>> getParentDetails(){
         List<ParentDetails> response;
       List<ParentDetailsDto> parents = parentService.getAllParents();
       return parents.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.status(HttpStatus.OK).body(parents);
    }

    @GetMapping("{parentId}")
    public ResponseEntity <ParentDetails> getParentDetails(@PathVariable Integer parentId){
         ParentDetails response;
        Optional<ParentDetailsDto> Parent = parentService.getParentById(parentId);
        return null;


    }
    @GetMapping("guardian/{studentId}")
    public ResponseEntity< ParentDetails> getparentDeatailsWithParentId(@PathVariable Integer studentId){
         ParentDetails response;
        Optional<ParentDetailsDto> Parent = parentService.getParentByStudentId(studentId);
        return null;


    }

    @PostMapping("addParentDetails")
    public ResponseEntity<ResponseDto> addParent(@RequestBody ParentDetailsDto Parent){
        parentService.addParent(Parent);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ParentsConstants.STATUS_201, ParentsConstants.MESSAGE_201));
    }
    @DeleteMapping("{parentID}")
    public ResponseEntity <Boolean> deleteParent(@PathVariable Integer parentID){
        parentService.deleteByParentId(parentID);
         Boolean response;
        return null;


    }



}
