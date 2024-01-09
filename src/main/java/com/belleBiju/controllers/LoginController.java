package com.belleBiju.controllers;

import com.belleBiju.DTOs.requests.LoginRequest;
import com.belleBiju.DTOs.responses.UserResponse;
import com.belleBiju.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/authentication")
@CrossOrigin(value = "*")
public class LoginController {



    private final LoginService service;

    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UserResponse> authenticate(@RequestBody LoginRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.isAuthetenticate(request));
    }

}
