package com.belleBiju.service;


import com.belleBiju.DTOs.requests.LoginRequest;
import com.belleBiju.DTOs.responses.UserResponse;
import com.belleBiju.entities.User;
import com.belleBiju.repository.UserRepository;
import com.belleBiju.security.EncryptPassword;
import com.belleBiju.service.exceptions.EntityNotFound;
import com.belleBiju.service.exceptions.UnauthorizedPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepository repository;

    @Autowired
    EncryptPassword encoder;


    public UserResponse isAuthetenticate(LoginRequest request) {
        Optional<User> findUser = Optional.ofNullable(Optional.ofNullable(repository.findByUsername(request.getUsername()))
                .orElseThrow(() -> new EntityNotFound("Usuario não encontrado")));

        if (validatePassword(request.getPassword(), findUser.get().getPassword())) {
            return new UserResponse(findUser.get());
        } else {
            throw new UnauthorizedPassword("Senha incorreta");
        }
    }

    public boolean validatePassword(CharSequence rawPassword, String encodedPassword){
        return encoder.passwordEncoder().matches(rawPassword, encodedPassword);
    }



}
