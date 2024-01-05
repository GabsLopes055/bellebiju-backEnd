package com.belleBiju.security;

import com.belleBiju.DTOs.requests.UserRequest;
import com.belleBiju.DTOs.responses.UserResponse;
import com.belleBiju.entities.User;
import com.belleBiju.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponse SaveNewUser(UserRequest request) {

        User user = new User();

        user.setNome(request.getNome());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        repository.save(user);

        return new UserResponse(user);

    }

}
