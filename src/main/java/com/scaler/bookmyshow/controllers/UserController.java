package com.scaler.bookmyshow.controllers;


import com.scaler.bookmyshow.dtos.CreateUserRequestDto;
import com.scaler.bookmyshow.dtos.CreateUserResponseDto;
import com.scaler.bookmyshow.models.User;
import com.scaler.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto requestDto) {
        User user = userService.createUser(requestDto.getEmail());

        CreateUserResponseDto responseDto = new CreateUserResponseDto();
        responseDto.setUser(user);
        return  responseDto;
    }
}
