package com.belleBiju.DTOs.responses;


import com.belleBiju.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserResponse {

    private UUID idUser;

    private String nome;

    private String username;

    private LocalDateTime createdAt;

    public UserResponse(User user) {
        this.idUser = user.getIdUser();
        this.nome = user.getNome();
        this.username = user.getUsername();
        this.createdAt = user.getCreatedAt();
    }
}
