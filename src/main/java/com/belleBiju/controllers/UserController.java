package com.belleBiju.controllers;

import com.belleBiju.DTOs.requests.UserRequest;
import com.belleBiju.DTOs.responses.UserResponse;
import com.belleBiju.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/users")
@CrossOrigin("*")
public class UserController {


    private final UserService service;

    UserController(UserService service){
        this.service = service;
    }

    /*Metodo para salvar usuario*/
    @PostMapping
    public ResponseEntity<UserResponse> saveNewUser(@RequestBody @Valid UserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.SaveNewUser(request));
    }

    /*metodo para listar todos os usuarios*/
    @GetMapping
    public ResponseEntity<List<UserResponse>> listAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(service.ListAllUsers());
    }

    /*Metodo para editar um usuario*/
    @PutMapping(value = "{idUser}")
    public ResponseEntity<UserResponse> editUsuario(@PathVariable(value = "idUser") UUID idUser, @RequestBody UserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.editUser(request, idUser));
    }

    /*Metodo para excluir usuario*/
    @DeleteMapping(value = "{idUser}")
    public ResponseEntity<String> deletarUsuario(@PathVariable(value = "idUser") UUID idUser) {
        if(service.deletarUsuario(idUser)) {
            return ResponseEntity.status(HttpStatus.OK).body("Usuario Deletado");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível excluir usuario");
        }
    }

}
