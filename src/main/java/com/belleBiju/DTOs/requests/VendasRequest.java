package com.belleBiju.DTOs.requests;

import com.belleBiju.entities.Enums.FormaPagamento;
import com.belleBiju.entities.Vendas;

public class VendasRequest {

    private String nomeProduto;

    private float preco;

    private int quantidade;

    private float total;

    private FormaPagamento formaPagamento;


    public Vendas toModel(Vendas request){
        return new Vendas(request.getNomeProduto(), request.getPreco(), request.getQuantidade(), request.getTotal(), request.getFormaPagamento());
    }

    public VendasRequest(String nomeProduto, float preco, int quantidade, float total, FormaPagamento formaPagamento) {
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.quantidade = quantidade;
        this.total = total;
        this.formaPagamento = formaPagamento;
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
}
