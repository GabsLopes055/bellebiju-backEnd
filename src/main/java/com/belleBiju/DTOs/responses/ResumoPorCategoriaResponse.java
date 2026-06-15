package com.belleBiju.DTOs.responses;

import java.util.UUID;

public class ResumoPorCategoriaResponse {

    private UUID idCategoria;
    private String nomeCategoria;
    private Long totalVendas;
    private Double receitaTotal;
    private Double ticketMedio;

    public ResumoPorCategoriaResponse(UUID idCategoria, String nomeCategoria,
                                       Long totalVendas, Double receitaTotal, Double ticketMedio) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.totalVendas = totalVendas;
        this.receitaTotal = receitaTotal;
        this.ticketMedio = ticketMedio;
    }

    public UUID getIdCategoria() { return idCategoria; }
    public String getNomeCategoria() { return nomeCategoria; }
    public Long getTotalVendas() { return totalVendas; }
    public Double getReceitaTotal() { return receitaTotal; }
    public Double getTicketMedio() { return ticketMedio; }
}
