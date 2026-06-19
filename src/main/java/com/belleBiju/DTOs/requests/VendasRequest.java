package com.belleBiju.DTOs.requests;

import com.belleBiju.entities.Enums.FormaPagamento;
import com.belleBiju.entities.Vendas;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.UUID;

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

    private UUID idProduto;

    private LocalDateTime dataVenda;

    public Vendas toModel(VendasRequest request) {
        Vendas response = new Vendas();
        response.setNomeProduto(request.getNomeProduto());
        response.setPreco(request.getPreco());
        response.setQuantidade(request.getQuantidade());
        response.setTotal(request.getTotal());
        response.setFormaPagamento(request.formaPagamento);
        if (request.getDataVenda() != null) {
            response.setCreateAt(request.getDataVenda());
        }
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

    public UUID getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(UUID idProduto) {
        this.idProduto = idProduto;
    }

    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }
}
