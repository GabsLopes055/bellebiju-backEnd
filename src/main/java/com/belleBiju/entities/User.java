package com.belleBiju.entities;
import com.belleBiju.entities.Enums.ROLES_PERMISSIONS;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
@NoArgsConstructor
public class User {

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
    private List<ROLES_PERMISSIONS> roles = new ArrayList<>();

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

    public void setUsername(String username) {
        this.username = username;
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

    public List<ROLES_PERMISSIONS> getRoles() {
        return roles;
    }

    public void setRoles(List<ROLES_PERMISSIONS> roles) {
        this.roles = roles;
    }
}
