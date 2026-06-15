package com.belleBiju.controllers;

import com.belleBiju.DTOs.requests.LoginRequest;
import com.belleBiju.DTOs.responses.AuthenticationToken;
import com.belleBiju.entities.User;
import com.belleBiju.service.LoginService;
import com.belleBiju.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/authentication")
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
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (!this.service.findByUser(request.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário ou senha incorretos");
        }

        var usernamePassword = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        AuthenticationToken response = new AuthenticationToken();
        response.AuthenticationToken(token);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        String newToken = tokenService.generateToken(user);

        AuthenticationToken response = new AuthenticationToken();
        response.AuthenticationToken(newToken);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
