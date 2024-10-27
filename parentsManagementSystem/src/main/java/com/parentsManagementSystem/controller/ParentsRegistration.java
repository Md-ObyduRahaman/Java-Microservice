package com.parentsManagementSystem.controller;



import com.parentsManagementSystem.constants.ParentsConstants;
import com.parentsManagementSystem.dto.ParentDetailsDto;
import com.parentsManagementSystem.dto.ResponseDto;
import com.parentsManagementSystem.entity.ParentDetails;
import com.parentsManagementSystem.service.ParentServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Tag(name = "Parents", description = "Parents related endpoints, parent details registration and retrieval")
@RestController
@RequestMapping("/Parent")
public class ParentsRegistration {

    private ParentServiceInterface parentService;

    public ParentsRegistration(ParentServiceInterface parentService) {
        this.parentService = parentService;
    }


    @Operation(
            summary = "Get all parent details",
            description = "This endpoint returns all parent details"
    )
    @GetMapping("/allParentDetails")
    public ResponseEntity< List<ParentDetailsDto>> getParentDetails(){
       List<ParentDetailsDto> parents = parentService.getAllParents();
       return ResponseEntity.status(HttpStatus.OK).body(parents);
    }

    @Operation(
            summary = "Get parent details by parent id",
            description = "This endpoint returns parent details by parent id"
    )
    @GetMapping("{parentId}")
    public ResponseEntity <ParentDetailsDto> getParentDetails(@PathVariable Integer parentId){
         ParentDetails response;
        Optional<ParentDetailsDto> Parent = parentService.getParentById(parentId);
        return ResponseEntity.status(HttpStatus.OK).body(Parent.get());


    }
    @Operation(
            summary = "Get parent details by student id",
            description = "This endpoint returns parent details by student id"
    )
    @GetMapping("guardian/{studentId}")
    public ResponseEntity< ParentDetailsDto> getparentDeatailsWithParentId(@PathVariable Integer studentId){
         ParentDetails response;
        Optional<ParentDetailsDto> Parent = parentService.getParentByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(Parent.get());


    }

    @Operation(
            summary = "Add parent details",
            description = "This endpoint adds parent details"
    )
    @PostMapping("addParentDetails")
    public ResponseEntity<ResponseDto> addParent(@Valid @RequestBody ParentDetailsDto Parent){
        parentService.addParent(Parent);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(ParentsConstants.STATUS_201, ParentsConstants.MESSAGE_201));
    }
    @Operation(
            summary = "Delete parent details",
            description = "This endpoint Deletes parent details"
    )
    @DeleteMapping("{parentID}")
    public ResponseEntity <ResponseDto> deleteParent(@PathVariable Integer parentID){
        boolean isDeleted = parentService.deleteByParentId(parentID);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ParentsConstants.STATUS_200, ParentsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ParentsConstants.STATUS_417, ParentsConstants.MESSAGE_417_DELETE));
        }
    }

@Operation(
    summary = "Update Parent  Details",
    description = "This endpoint updates the Parent details of a parent"
)
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateParentsDetails(@Valid @RequestBody ParentDetailsDto parentDetailsDto) {
        boolean isUpdated = parentService.updateParent(parentDetailsDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ParentsConstants.STATUS_200, ParentsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ParentsConstants.STATUS_417, ParentsConstants.MESSAGE_417_UPDATE));
        }
    }
}
