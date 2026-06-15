package com.belleBiju.DTOs.requests;

import jakarta.validation.constraints.NotNull;

public class AjusteEstoqueRequest {

    @NotNull(message = "A quantidade não pode ser nula")
    private Integer quantidade;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
