package com.digitalhouse.users_service.services;

import com.digitalhouse.users_service.model.dtos.UserRequest;
import com.digitalhouse.users_service.model.dtos.UserResponse;
import com.digitalhouse.users_service.model.entities.User;
import com.digitalhouse.users_service.repositories.UserRepository;
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

//CREATE USER
    public void addUser(UserRequest userRequest) {
        var user = User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .dni(userRequest.getDni())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword()) //ENCRIPT PASSWORD
                .phone(userRequest.getPhone())
                .build();
        userRepository.save(user);
        log.info("User added: {}", user);
    }

    //DELETE USER
   /* public void deleteUser(Long id) throws ResourceNotFoundException {
           Optional<User> user = userRepository.findById(id);
            if (user.isEmpty())

                throw new ResourceNotFoundException("No existe el usuario con id: " + id);

            userRepository.deleteById(id);

            log.info("Se elimina el usuario con Id: " + id);
        }*/

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