package com.belleBiju.DTOs.requests;

import com.belleBiju.entities.Enums.ROLES_PERMISSIONS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;
import java.util.UUID;

public class UserRequest {

    @NotBlank(message = "O campo nome não pode estar vazio")
    @NotNull(message = "O nome do usuário não pode ser em branco.")
    private String nome;

    @NotBlank(message = "O campo username não pode estar vazio")
    @NotNull(message = "O nome do username não pode ser em branco.")
    private String username;

    @NotBlank(message = "O campo password não pode estar vazio")
    @NotNull(message = "O nome do password não pode ser em branco.")
    private String password;

    private ROLES_PERMISSIONS roles;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ROLES_PERMISSIONS getRoles() {
        return roles;
    }

    public void setRoles(ROLES_PERMISSIONS roles) {
        this.roles = roles;
    }
}
