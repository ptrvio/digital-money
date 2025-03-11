package com.digitalhouse.users_service.services;


import com.digitalhouse.users_service.exceptions.NotFoundUserException;
import com.digitalhouse.users_service.model.dtos.UserRequest;
import com.digitalhouse.users_service.model.dtos.UserResponse;
import com.digitalhouse.users_service.model.entities.Account;
import com.digitalhouse.users_service.model.entities.User;
import com.digitalhouse.users_service.repositories.AccountRepository;
import com.digitalhouse.users_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

@Autowired
    private final UserRepository userRepository;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

//CREATE USER
    public UserResponse createUser(UserRequest userRequest){

        Optional<User> user1 = userRepository.findByEmail(userRequest.getEmail());

        if(user1.isPresent()) {
            log.error("Error al crear un usuario que ya existe");
            throw new IllegalArgumentException("El usuario con email: " + userRequest.getEmail() + " ya existe" );
        }
        try{
            var user = User.builder()
                .firstname(userRequest.getFirstname())
                .lastname(userRequest.getLastname())
                .dni(userRequest.getDni())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword()) //ENCRIPT PASSWORD
                .phone(userRequest.getPhone())
                .build();

            userRepository.save(user);
            accountService.createAccount(user);
            log.info("User created: {}", user);
            return mapToUserResponse(user);

        }catch (Exception e){
            Optional<User> user = userRepository.findByEmail(userRequest.getEmail());
            Optional<Account> account = accountRepository.findByUserId(user.get().getId());
            if(user.isPresent() && account.isEmpty()){
                userRepository.deleteById(user.get().getId());
            }
            throw e;
        }
    }

    public UserResponse getUserById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundUserException("El usuario " + id + " no existe");
        }
        var user2 = User.builder()
                .id(user.get().getId())
                .firstname(user.get().getFirstname())
                .lastname(user.get().getLastname())
                .dni(user.get().getDni())
                .email(user.get().getEmail())
                .phone(user.get().getPhone())
                .build();
    return mapToUserResponse(user2);
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
                    .phone(user.getPhone())
                    .build();
    }
}