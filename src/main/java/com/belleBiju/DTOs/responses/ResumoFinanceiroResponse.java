package com.belleBiju.DTOs.responses;

public class ResumoFinanceiroResponse {

    private String dataInicio;
    private String dataFim;
    private Long totalVendas;
    private Double receitaTotal;
    private Double ticketMedio;

    public ResumoFinanceiroResponse(String dataInicio, String dataFim, Long totalVendas, Double receitaTotal, Double ticketMedio) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.totalVendas = totalVendas;
        this.receitaTotal = receitaTotal;
        this.ticketMedio = ticketMedio;
    }

    public String getDataInicio() { return dataInicio; }
    public String getDataFim() { return dataFim; }
    public Long getTotalVendas() { return totalVendas; }
    public Double getReceitaTotal() { return receitaTotal; }
    public Double getTicketMedio() { return ticketMedio; }
}
