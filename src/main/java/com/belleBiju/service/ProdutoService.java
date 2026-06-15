package com.belleBiju.service;

import com.belleBiju.DTOs.requests.AjusteEstoqueRequest;
import com.belleBiju.DTOs.requests.ProdutoRequest;
import com.belleBiju.DTOs.responses.PaginaResponse;
import com.belleBiju.DTOs.responses.ProdutoResponse;
import com.belleBiju.entities.Categoria;
import com.belleBiju.entities.Produto;
import com.belleBiju.repository.CategoriaRepository;
import com.belleBiju.repository.ProdutoRepository;
import com.belleBiju.service.exceptions.EntityNotFound;
import com.belleBiju.service.exceptions.EstoqueInsuficiente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private static final Logger log = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository repository;
    private final CategoriaRepository categoriaRepository;

    ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    public ProdutoResponse salvarNovoProduto(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNomeProduto(request.getNomeProduto());
        produto.setPrecoProduto(request.getPrecoProduto());
        produto.setCategoria(resolverCategoria(request.getIdCategoria()));
        if (request.getQuantidadeEstoque() != null) {
            produto.setQuantidadeEstoque(request.getQuantidadeEstoque());
        }
        if (request.getEstoqueMinimo() != null) {
            produto.setEstoqueMinimo(request.getEstoqueMinimo());
        }
        Produto salvo = repository.save(produto);
        log.info("Produto criado | id: {} | nome: {}", salvo.getIdProduto(), salvo.getNomeProduto());
        return new ProdutoResponse(salvo);
    }

    public PaginaResponse<ProdutoResponse> listarTodosProdutos(int pagina, int tamanho) {
        return new PaginaResponse<>(
                repository.findAll(PageRequest.of(pagina, tamanho))
                        .map(ProdutoResponse::new)
        );
    }

    public ProdutoResponse buscarPorId(UUID id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Produto não encontrado para o ID: " + id));
        return new ProdutoResponse(produto);
    }

    public List<ProdutoResponse> buscarPorNome(String nome) {
        return repository.findByNomeProdutoContainingIgnoreCase(nome).stream()
                .map(ProdutoResponse::new)
                .collect(Collectors.toList());
    }

    public ProdutoResponse editarProduto(UUID id, ProdutoRequest request) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Produto não encontrado para o ID: " + id));
        produto.setNomeProduto(request.getNomeProduto());
        produto.setPrecoProduto(request.getPrecoProduto());
        produto.setCategoria(resolverCategoria(request.getIdCategoria()));
        if (request.getQuantidadeEstoque() != null) {
            produto.setQuantidadeEstoque(request.getQuantidadeEstoque());
        }
        if (request.getEstoqueMinimo() != null) {
            produto.setEstoqueMinimo(request.getEstoqueMinimo());
        }
        return new ProdutoResponse(repository.save(produto));
    }

    public void deletarProduto(UUID id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Produto não encontrado para o ID: " + id));
        log.info("Produto excluído | id: {} | nome: {}", produto.getIdProduto(), produto.getNomeProduto());
        repository.delete(produto);
    }

    public ProdutoResponse ajustarEstoque(UUID id, AjusteEstoqueRequest request) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFound("Produto não encontrado para o ID: " + id));
        int estoqueAnterior = produto.getQuantidadeEstoque();
        int novoEstoque = estoqueAnterior + request.getQuantidade();
        if (novoEstoque < 0) {
            throw new EstoqueInsuficiente("Ajuste inválido. Estoque disponível: "
                    + estoqueAnterior + ", ajuste solicitado: " + request.getQuantidade());
        }
        produto.setQuantidadeEstoque(novoEstoque);
        Produto salvo = repository.save(produto);
        log.info("Estoque ajustado | produto: {} | de: {} para: {}", produto.getNomeProduto(), estoqueAnterior, novoEstoque);
        return new ProdutoResponse(salvo);
    }

    public List<ProdutoResponse> listarEstoqueBaixo() {
        return repository.findEstoqueBaixo().stream()
                .map(ProdutoResponse::new)
                .collect(Collectors.toList());
    }

    private Categoria resolverCategoria(UUID idCategoria) {
        if (idCategoria == null) return null;
        return categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new EntityNotFound("Categoria não encontrada para o ID: " + idCategoria));
    }
}
