package com.belleBiju.DTOs.responses;

import com.belleBiju.repository.VendasRepository.ProdutoMaisVendidoProjection;

public class ProdutoMaisVendidoResponse {

    private String nomeProduto;
    private Long quantidadeTotal;
    private Double receitaTotal;

    public ProdutoMaisVendidoResponse(ProdutoMaisVendidoProjection projection) {
        this.nomeProduto = projection.getNomeProduto();
        this.quantidadeTotal = projection.getQuantidadeTotal();
        this.receitaTotal = projection.getReceitaTotal();
    }

    public String getNomeProduto() { return nomeProduto; }
    public Long getQuantidadeTotal() { return quantidadeTotal; }
    public Double getReceitaTotal() { return receitaTotal; }
}
