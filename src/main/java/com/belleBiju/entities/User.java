package com.belleBiju.entities;

import com.belleBiju.entities.Enums.FormaPagamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tbl_user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idUser;

    private String nomeProduto;

    private float preco;

    private int quantidade;

    private float total;

    private FormaPagamento formaPagamento;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    @PrePersist
    public void prePersist(){
        this.setCreatedAt(LocalDateTime.now());
        this.setUpdateAt(LocalDateTime.now());
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt(){
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt){
        this.updateAt = updateAt;
    }

}
