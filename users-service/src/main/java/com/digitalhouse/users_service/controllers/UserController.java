package com.digitalhouse.users_service.controllers;


import com.digitalhouse.users_service.model.dtos.UserResponse;
import com.digitalhouse.users_service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @RequestMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return this.userService.getAllUsers();
    }
    @PutMapping("/editUsers")
    @RequestMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> editUser(@RequestBody UserResponse userResponse) throws Exception{
        return null;
    }
}