package com.belleBiju.DTOs.responses;

import com.belleBiju.entities.Categoria;

import java.util.UUID;

public class CategoriaResponse {

    private UUID idCategoria;
    private String nomeCategoria;

    public CategoriaResponse(Categoria categoria) {
        this.idCategoria = categoria.getIdCategoria();
        this.nomeCategoria = categoria.getNomeCategoria();
    }

    public UUID getIdCategoria() {
        return idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
