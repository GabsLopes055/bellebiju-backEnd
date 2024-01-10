package com.belleBiju.DTOs.requests;

import com.belleBiju.entities.Enums.FormaPagamento;
import com.belleBiju.entities.Vendas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VendasRequest {

    @NotBlank(message = "O campo Nome do produto não pode ser vazio")
    @NotNull(message = "Nome do produto esta ausente")
    private String nomeProduto;

    @NotNull(message = "Preco da venda esta ausente")
    @Positive
    private float preco;

    @NotNull(message = "A Quantidade esta ausente")
    @Positive
    private int quantidade;

    @NotNull(message = "Total da venda esta ausente")
    @Positive
    private float total;

    @NotNull(message = "Forma de Pagamento esta ausente")
    private FormaPagamento formaPagamento;


    public Vendas toModel(VendasRequest request) {
        Vendas response = new Vendas();
        response.setNomeProduto(request.getNomeProduto());
        response.setPreco(request.getPreco());
        response.setQuantidade(request.getQuantidade());
        response.setTotal(request.getTotal());
        response.setFormaPagamento(request.formaPagamento);
        return response;
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
