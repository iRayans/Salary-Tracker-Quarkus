package com.rayan.salarytracker.authentication;

import com.rayan.salarytracker.dto.user.UserLoginDTO;
import com.rayan.salarytracker.service.impl.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthenticationProvider {

    @Inject
    UserService userService;

    public boolean authenticate(UserLoginDTO userLoginDTO)  {
        return userService.isUserValid(userLoginDTO.getEmail(), userLoginDTO.getPassword());
    }
}
