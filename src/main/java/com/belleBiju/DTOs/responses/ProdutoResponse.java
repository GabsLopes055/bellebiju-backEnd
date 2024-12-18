package com.belleBiju.DTOs.responses;

import com.belleBiju.entities.Produto;
import com.belleBiju.repository.ProdutoRepository;
import lombok.Data;

import java.util.UUID;

@Data
public class ProdutoResponse {

    private UUID idProduto;

    private String nomeProduto;

    private Double precoProduto;

    public ProdutoResponse(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getNomeProduto();
        this.precoProduto = produto.getPrecoProduto();
    }

    public ProdutoResponse toModel(Produto produto) {
        return new ProdutoResponse(produto);
    }


}
