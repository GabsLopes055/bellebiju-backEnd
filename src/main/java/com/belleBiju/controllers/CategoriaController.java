package com.belleBiju.controllers;

import com.belleBiju.DTOs.requests.CategoriaRequest;
import com.belleBiju.DTOs.responses.CategoriaResponse;
import com.belleBiju.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/categoria")
public class CategoriaController {

    private final CategoriaService service;

    CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> criarCategoria(@RequestBody @Valid CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarCategoria(request));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarCategorias() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarCategorias());
    }

    @PutMapping("/{idCategoria}")
    public ResponseEntity<CategoriaResponse> editarCategoria(
            @PathVariable UUID idCategoria,
            @RequestBody @Valid CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.editarCategoria(idCategoria, request));
    }

    @DeleteMapping("/{idCategoria}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable UUID idCategoria) {
        service.deletarCategoria(idCategoria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
