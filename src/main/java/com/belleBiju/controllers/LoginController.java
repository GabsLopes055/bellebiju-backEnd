package com.belleBiju.controllers;

import com.belleBiju.DTOs.requests.LoginRequest;
import com.belleBiju.DTOs.responses.AuthenticationToken;
import com.belleBiju.DTOs.responses.UserResponse;
import com.belleBiju.entities.User;
import com.belleBiju.service.LoginService;
import com.belleBiju.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/authentication")
@CrossOrigin(value = "*")
public class LoginController {



    private final LoginService service;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    public LoginController(LoginService service, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationToken> login(@RequestBody LoginRequest request) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        AuthenticationToken response = new AuthenticationToken();

        response.AuthenticationToken(token);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

}
