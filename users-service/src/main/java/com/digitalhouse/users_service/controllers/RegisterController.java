package com.digitalhouse.users_service.controllers;


import com.digitalhouse.users_service.exceptions.BadRequestUserException;
import com.digitalhouse.users_service.model.dtos.UserRequest;
import com.digitalhouse.users_service.model.dtos.UserResponse;
import com.digitalhouse.users_service.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;


    @PostMapping
    @RequestMapping()
    public ResponseEntity <?> registerUser(@Valid @RequestBody UserRequest userRequest) {

        try{

            UserResponse userResponse = userService.createUser(userRequest);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userResponse);

        } catch (BadRequestUserException e) {
            log.error("Datos invalidos para crear usuario");
            throw new BadRequestUserException("Datos invalidos para crear usuario");
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
                throw new BadRequestUserException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}