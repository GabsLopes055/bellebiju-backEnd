package com.belleBiju.controllers;


import com.belleBiju.DTOs.requests.GraficoPizzaRequest;
import com.belleBiju.DTOs.responses.GraficoPizzaResponse;
import com.belleBiju.service.GraficoPizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/graficos")
@CrossOrigin(value = "*")
public class GraficoPizzaController {

    private final GraficoPizzaService graficoPizzaService;

    GraficoPizzaController(GraficoPizzaService graficoPizzaService) {
        this.graficoPizzaService = graficoPizzaService;
    }

    @PostMapping(value = "/gerarGraficoPizza")
    public ResponseEntity<?> gerarGraficoPizza(@RequestBody GraficoPizzaRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.graficoPizzaService.gerarGraficoPizza(request));
    }

    @PostMapping(value = "/gerarGraficoTotalVendas")
    public ResponseEntity<?> gerarGraficoTotalVendas(@RequestBody GraficoPizzaRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.graficoPizzaService.gerarGraficoTotalVendas(request));
    }

}