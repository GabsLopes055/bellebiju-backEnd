package com.belleBiju.DTOs.requests;


import java.util.Date;

public class GraficoPizzaRequest {

    private String dataInicio;

    private String dataFim;

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}
