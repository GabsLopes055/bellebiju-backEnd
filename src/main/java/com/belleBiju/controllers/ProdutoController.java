package com.belleBiju.controllers;

import com.belleBiju.DTOs.requests.AjusteEstoqueRequest;
import com.belleBiju.DTOs.requests.ProdutoRequest;
import com.belleBiju.DTOs.responses.PaginaResponse;
import com.belleBiju.DTOs.responses.ProdutoResponse;
import com.belleBiju.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {

    private final ProdutoService service;

    ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PaginaResponse<ProdutoResponse>> listarTodosProdutos(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarTodosProdutos(pagina, tamanho));
    }

    @GetMapping("/estoque-baixo")
    public ResponseEntity<List<ProdutoResponse>> listarEstoqueBaixo() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listarEstoqueBaixo());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutoResponse>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorNome(nome));
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable UUID idProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(idProduto));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> salvarNovoProduto(@RequestBody @Valid ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarNovoProduto(request));
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoResponse> editarProduto(
            @PathVariable UUID idProduto,
            @RequestBody @Valid ProdutoRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.editarProduto(idProduto, request));
    }

    @PatchMapping("/{idProduto}/estoque")
    public ResponseEntity<ProdutoResponse> ajustarEstoque(
            @PathVariable UUID idProduto,
            @RequestBody @Valid AjusteEstoqueRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.ajustarEstoque(idProduto, request));
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID idProduto) {
        service.deletarProduto(idProduto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
