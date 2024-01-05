package com.belleBiju.service;

import com.belleBiju.DTOs.requests.UserRequest;
import com.belleBiju.DTOs.responses.UserResponse;
import com.belleBiju.entities.User;
import com.belleBiju.repository.UserRepository;
import com.belleBiju.security.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EncryptPassword encode;


    /* Metodo para salvar um usuario */
    public UserResponse SaveNewUser(UserRequest request) {

        User user = new User();

        user.setNome(request.getNome());
        user.setUsername(request.getUsername());
        user.setPassword(encode.EncryptPassword().encode(request.getPassword())); // criptografando a senha do usuario

        repository.save(user);

        return new UserResponse(user);

    }

}
