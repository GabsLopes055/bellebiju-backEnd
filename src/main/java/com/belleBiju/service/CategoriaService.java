package com.belleBiju.service;

import com.belleBiju.DTOs.requests.CategoriaRequest;
import com.belleBiju.DTOs.responses.CategoriaResponse;
import com.belleBiju.entities.Categoria;
import com.belleBiju.repository.CategoriaRepository;
import com.belleBiju.service.exceptions.EntityNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public CategoriaResponse criarCategoria(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(request.getNomeCategoria());
        return new CategoriaResponse(repository.save(categoria));
    }

    public List<CategoriaResponse> listarCategorias() {
        return repository.findAll().stream()
                .map(CategoriaResponse::new)
                .collect(Collectors.toList());
    }

    public CategoriaResponse editarCategoria(UUID id, CategoriaRequest request) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Categoria não encontrada para o ID: " + id));
        categoria.setNomeCategoria(request.getNomeCategoria());
        return new CategoriaResponse(repository.save(categoria));
    }

    public void deletarCategoria(UUID id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Categoria não encontrada para o ID: " + id));
        repository.delete(categoria);
    }
}
