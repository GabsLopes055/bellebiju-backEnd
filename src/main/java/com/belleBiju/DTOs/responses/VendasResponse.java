package com.belleBiju.DTOs.responses;

import com.belleBiju.entities.Enums.FormaPagamento;
import com.belleBiju.entities.Vendas;

import java.time.LocalDateTime;
import java.util.UUID;

public class VendasResponse {

    private UUID id;

    private String nomeProduto;

    private float preco;

    private int quantidade;

    private float total;

    private FormaPagamento formaPagamento;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public VendasResponse toModelResponse(Vendas vendas) {

        VendasResponse response = new VendasResponse();

        response.setId(vendas.getId());
        response.setNomeProduto(vendas.getNomeProduto());
        response.setPreco(vendas.getPreco());
        response.setQuantidade(vendas.getQuantidade());
        response.setTotal(vendas.getTotal());
        response.setFormaPagamento(vendas.getFormaPagamento());
        response.setCreateAt(vendas.getCreateAt());
        response.setUpdateAt(vendas.getUpdateAt());

        return response;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
