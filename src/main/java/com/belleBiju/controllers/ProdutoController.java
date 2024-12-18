package com.belleBiju.controllers;

import com.belleBiju.DTOs.requests.ProdutoRequest;
import com.belleBiju.DTOs.responses.ProdutoResponse;
import com.belleBiju.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produto")
@CrossOrigin(value = "*")
public class ProdutoController {

    private final ProdutoService service;

    ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarTodosProdutos() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodosProdutos());
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> salvarNovoProduto(@RequestBody @Valid ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarNovoProduto(request));
    }
//
//    @DeleteMapping
//    public


}
