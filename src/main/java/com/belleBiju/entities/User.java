package com.belleBiju.entities;
import com.belleBiju.entities.Enums.ROLES_PERMISSIONS;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;

    @NotNull
    @NotBlank(message = "Campo obrigatório: nome")
    private String nome;

    @NotNull
    @NotBlank(message = "Campo obrigatório: username")
    private String username;

    @NotNull
    @NotBlank(message = "Campo obrigatório: password")
    private String password;

    @Enumerated
    private ROLES_PERMISSIONS roles;

    private LocalDateTime createdAt;

    public UUID getIdUser() {
        return  idUser;
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

    public ROLES_PERMISSIONS getRoles() {
        return roles;
    }

    public void setRoles(ROLES_PERMISSIONS roles) {
        this.roles = roles;
    }
}
