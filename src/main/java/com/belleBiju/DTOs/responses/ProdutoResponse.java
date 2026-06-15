package com.belleBiju.DTOs.responses;

import com.belleBiju.entities.Produto;

import java.util.UUID;

public class ProdutoResponse {

    private UUID idProduto;
    private String nomeProduto;
    private Double precoProduto;
    private Integer quantidadeEstoque;
    private Integer estoqueMinimo;
    private CategoriaResponse categoria;

    public ProdutoResponse(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getNomeProduto();
        this.precoProduto = produto.getPrecoProduto();
        this.quantidadeEstoque = produto.getQuantidadeEstoque();
        this.estoqueMinimo = produto.getEstoqueMinimo();
        this.categoria = produto.getCategoria() != null
                ? new CategoriaResponse(produto.getCategoria())
                : null;
    }

    public UUID getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public Integer getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }
}
