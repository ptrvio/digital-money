package com.digitalhouse.users_service.controllers;


import com.digitalhouse.users_service.model.dtos.UserRequest;
import com.digitalhouse.users_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;


    @PostMapping
    @RequestMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody UserRequest userRequest) {
            this.userService.createUser(userRequest);
    }
}