package com.belleBiju.service;

import com.belleBiju.DTOs.requests.GraficoPizzaRequest;
import com.belleBiju.DTOs.responses.GraficoPizzaResponse;
import com.belleBiju.repository.VendasRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class GraficoPizzaService {

    private final VendasRepository repository;

    GraficoPizzaService(VendasRepository repository) {
        this.repository = repository;
    }

    public Object gerarGraficoPizza(GraficoPizzaRequest request) {
        return this.repository.gerarGraficoPizza(LocalDate.parse(request.getDataInicio()), LocalDate.parse(request.getDataFim()));
    }

    public Object gerarGraficoTotalVendas(GraficoPizzaRequest request) {
        return this.repository.gerarGraficoTotalVendas(LocalDate.parse(request.getDataInicio()), LocalDate.parse(request.getDataFim()));
    }

}
