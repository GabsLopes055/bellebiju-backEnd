package com.belleBiju.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tbl_produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idProduto;

    private String nomeProduto;

    private Double precoProduto;

}
