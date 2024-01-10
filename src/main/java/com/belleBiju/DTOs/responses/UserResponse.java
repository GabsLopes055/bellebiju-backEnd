package com.belleBiju.DTOs.responses;


import com.belleBiju.entities.Enums.ROLES_PERMISSIONS;
import com.belleBiju.entities.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserResponse {

    private UUID idUser;

    private String nome;

    private String username;

    private LocalDateTime createdAt;

    private ROLES_PERMISSIONS roles;

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ROLES_PERMISSIONS getRoles() {
        return roles;
    }

    public void setRoles(ROLES_PERMISSIONS roles) {
        this.roles = roles;
    }

    public UserResponse(User user) {
        this.idUser = user.getIdUser();
        this.nome = user.getNome();
        this.username = user.getUsername();
        this.createdAt = user.getCreatedAt();
        this.roles = user.getRoles();
    }

    public UserResponse() {

    }
}
