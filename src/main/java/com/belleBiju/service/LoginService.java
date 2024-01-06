package com.belleBiju.service;


import com.belleBiju.DTOs.requests.LoginRequest;
import com.belleBiju.DTOs.responses.UserResponse;
import com.belleBiju.entities.User;
import com.belleBiju.repository.UserRepository;
import com.belleBiju.security.EncryptPassword;
import com.belleBiju.service.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepository repository;

    @Autowired
    EncryptPassword encoder;


    public Object isAuthetenticate(LoginRequest request){

        Optional<User> findUser = Optional.ofNullable(Optional.ofNullable(repository.findByUsername(request.getUsername()))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado")));

        if(!validatePassword(request.getPassword(), findUser.get().getPassword())){
            return new EntityNotFoundException("Senha incorreta");
        }

        UserResponse user = new UserResponse();

        user.setIdUser(findUser.get().getIdUser());
        user.setNome(findUser.get().getNome());
        user.setUsername(findUser.get().getUsername());
        user.setCreatedAt(findUser.get().getCreatedAt());
        user.setRoles(findUser.get().getRoles());

        return user;


    }

    public boolean validatePassword(CharSequence rawPassword, String encodedPassword){
        return encoder.EncryptPassword().matches(rawPassword, encodedPassword);
    }



}
