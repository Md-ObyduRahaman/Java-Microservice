package com.schoolManagement.LoginSystem.controller;


import com.schoolManagement.LoginSystem.entity.AuthRequest;
import com.schoolManagement.LoginSystem.entity.UserInfo;
import com.schoolManagement.LoginSystem.exception.BaseResponse;
import com.schoolManagement.LoginSystem.exception.ResourceNotFoundException;
import com.schoolManagement.LoginSystem.service.JwtService;
import com.schoolManagement.LoginSystem.service.UserInfoDetails;
import com.schoolManagement.LoginSystem.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserInfo ssInfo;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/addNewUser")
    public ResponseEntity<BaseResponse<String>> addNewUser(@RequestBody UserInfo userInfo) {
        BaseResponse<String> response;
        if(service.addUser(userInfo).getEmail().isEmpty()){
            response = new BaseResponse<>(
                    "User not found", null, HttpStatus.BAD_GATEWAY, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
        }
        else {
            response = new BaseResponse<>(
                    "User Added Successfully", null, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<BaseResponse<UserInfo>> userProfile() {


        ssInfo.setId(12);
        ssInfo.setEmail("sojib@gmail.com");
        ssInfo.setName("Sojib");
                BaseResponse<UserInfo> response;
        if (false) {
            response = new BaseResponse<>(
                    "User not found", null, HttpStatus.BAD_GATEWAY, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
        } else {
            response = new BaseResponse<>(
                    "User found successfully", ssInfo, HttpStatus.OK, ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }



    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {

        return "Welcome to Admin Profile";
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
