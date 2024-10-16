package com.schoolManagement.LoginSystem.service;

import com.schoolManagement.LoginSystem.entity.UserInfo;
import com.schoolManagement.LoginSystem.exception.ResourceNotFoundException;
import com.schoolManagement.LoginSystem.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userDetail = repository.findByEmail(username); // Assuming 'email' is used as username
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

    }

    public UserInfo addUser(UserInfo userInfo) {

        UserInfo info;

        try {
            userInfo.setPassword(encoder.encode(userInfo.getPassword()));
            info=repository.save(userInfo);
        }
        catch (Exception e)
        {
            throw new ResourceNotFoundException("Duplicate entry :"  + userInfo.getEmail());
        }

        return info;
    }
}
