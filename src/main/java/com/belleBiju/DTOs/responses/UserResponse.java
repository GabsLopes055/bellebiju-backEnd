package com.belleBiju.DTOs.responses;


import com.belleBiju.entities.Enums.ROLES_PERMISSIONS;
import com.belleBiju.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserResponse {

    private UUID idUser;

    private String nome;

    private String username;

    private LocalDateTime createdAt;

    private List<ROLES_PERMISSIONS> roles;

    public UserResponse(User user) {
        this.idUser = user.getIdUser();
        this.nome = user.getNome();
        this.username = user.getUsername();
        this.createdAt = user.getCreatedAt();
        this.roles = user.getRoles();
    }
}
