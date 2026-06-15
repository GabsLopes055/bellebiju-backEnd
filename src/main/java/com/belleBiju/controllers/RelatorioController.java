package com.belleBiju.controllers;

import com.belleBiju.DTOs.responses.ProdutoMaisVendidoResponse;
import com.belleBiju.DTOs.responses.ResumoFinanceiroResponse;
import com.belleBiju.DTOs.responses.ResumoPorCategoriaResponse;
import com.belleBiju.service.RelatorioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService service;

    RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @GetMapping("/resumo")
    public ResponseEntity<ResumoFinanceiroResponse> gerarResumo(
            @RequestParam String dataInicio,
            @RequestParam String dataFim,
            @RequestParam(required = false) UUID idCategoria) {
        return ResponseEntity.status(HttpStatus.OK).body(service.gerarResumo(dataInicio, dataFim, idCategoria));
    }

    @GetMapping("/produtos-mais-vendidos")
    public ResponseEntity<List<ProdutoMaisVendidoResponse>> produtosMaisVendidos(
            @RequestParam String dataInicio,
            @RequestParam String dataFim,
            @RequestParam(required = false) UUID idCategoria) {
        return ResponseEntity.status(HttpStatus.OK).body(service.produtosMaisVendidos(dataInicio, dataFim, idCategoria));
    }

    @GetMapping("/resumo-por-categoria")
    public ResponseEntity<List<ResumoPorCategoriaResponse>> resumoPorCategoria(
            @RequestParam String dataInicio,
            @RequestParam String dataFim) {
        return ResponseEntity.status(HttpStatus.OK).body(service.resumoPorCategoria(dataInicio, dataFim));
    }
}
