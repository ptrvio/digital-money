package com.digitalhouse.users_service.controllers;


import com.digitalhouse.users_service.model.dtos.UserRequest;
import com.digitalhouse.users_service.model.dtos.UserResponse;
import com.digitalhouse.users_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping
    @RequestMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody UserRequest userRequest) {
        this.userService.addUser(userRequest);
    }

    @GetMapping
    @RequestMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return this.userService.getAllUsers();


    }

}