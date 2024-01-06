package com.belleBiju.controllers;

import com.belleBiju.DTOs.requests.LoginRequest;
import com.belleBiju.DTOs.responses.UserResponse;
import com.belleBiju.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/authentication")
@CrossOrigin("*")
public class LoginController {


    @Autowired
    LoginService service;

    @GetMapping
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(service.isAuthetenticate(request));
    }

}
