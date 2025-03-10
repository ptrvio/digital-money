package com.digitalhouse.users_service.services;


import com.digitalhouse.users_service.model.dtos.UserRequest;
import com.digitalhouse.users_service.model.dtos.UserResponse;
import com.digitalhouse.users_service.model.entities.User;
import com.digitalhouse.users_service.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

//CREATE USER
    public void createUser(@Valid UserRequest userRequest){

        Optional<User> user1 = userRepository.findByEmail(userRequest.getEmail());

        if(user1.isPresent()) {
            log.error("Error al crear un usuario que ya existe");
            throw new IllegalArgumentException("El usuario con email: " + userRequest.getEmail() + "ya existe" );
        }
        var user = User.builder()
                .firstname(userRequest.getFirstname())
                .dni(userRequest.getDni())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword()) //ENCRIPT PASSWORD
                .phone(userRequest.getPhone())
                .build();

        userRepository.save(user);
        accountService.createAccount(user);
        log.info("User created: {}", user);
        }



//LIST ALL USERS
    public List<UserResponse> getAllUsers() {

        var users = userRepository.findAll();

        return users.stream().map(this::mapToUserResponse).toList();
    }

    //MAP USER TO USER_RESPONSE
    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .dni(user.getDni())
                .email(user.getEmail())
                .password(user.getPassword())
                .phone(user.getPhone())
                .build();
    }
}