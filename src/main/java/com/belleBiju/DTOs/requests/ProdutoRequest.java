package com.belleBiju.DTOs.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProdutoRequest {

    @NotNull(message = "Nome do produto não pode ser vazio")
    private String nomeProduto;

    @NotNull(message = "Preço do produto não pode ser vazio")
    private Double precoProduto;

}
