package com.belleBiju.service;

import com.belleBiju.DTOs.requests.VendasRequest;
import com.belleBiju.DTOs.responses.PaginaResponse;
import com.belleBiju.DTOs.responses.VendasResponse;
import com.belleBiju.entities.Produto;
import com.belleBiju.entities.Vendas;
import com.belleBiju.repository.ProdutoRepository;
import com.belleBiju.repository.VendasRepository;
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
public class VendasService {

    private static final Logger log = LoggerFactory.getLogger(VendasService.class);

    private final VendasRepository repository;
    private final ProdutoRepository produtoRepository;

    private VendasService(VendasRepository repository, ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public VendasResponse createVenda(VendasRequest request) {
        Vendas venda = new VendasRequest().toModel(request);

        if (request.getIdProduto() != null) {
            Produto produto = produtoRepository.findById(request.getIdProduto())
                    .orElseThrow(() -> new EntityNotFound("Produto não encontrado para o ID: " + request.getIdProduto()));

            if (produto.getQuantidadeEstoque() < request.getQuantidade()) {
                log.warn("Tentativa de venda sem estoque | produto: {} | disponível: {} | solicitado: {}",
                        produto.getNomeProduto(), produto.getQuantidadeEstoque(), request.getQuantidade());
                throw new EstoqueInsuficiente("Estoque insuficiente. Disponível: "
                        + produto.getQuantidadeEstoque() + ", solicitado: " + request.getQuantidade());
            }

            float totalCalculado = produto.getPrecoProduto().floatValue() * request.getQuantidade();
            venda.setPreco(produto.getPrecoProduto().floatValue());
            venda.setTotal(totalCalculado);

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - request.getQuantidade());
            produtoRepository.save(produto);
            venda.setProduto(produto);
        }

        Vendas salva = repository.save(venda);
        log.info("Venda criada | produto: {} | quantidade: {} | total: {}",
                salva.getNomeProduto(), salva.getQuantidade(), salva.getTotal());
        return new VendasResponse().toModelResponse(salva);
    }

    public PaginaResponse<VendasResponse> listAllVendas(int pagina, int tamanho) {
        return new PaginaResponse<>(
                repository.findAllDesc(PageRequest.of(pagina, tamanho)).map(v -> new VendasResponse().toModelResponse(v))
        );
    }

    public List<VendasResponse> listVendasByDates(String inicio, String fim) {
        return repository.findByVendasWhereDate(inicio, fim).stream()
                .map(v -> new VendasResponse().toModelResponse(v))
                .collect(Collectors.toList());
    }

    public VendasResponse editVenda(VendasRequest request, UUID idVenda) {
        Vendas venda = repository.findById(idVenda)
                .orElseThrow(() -> new EntityNotFound("Venda não encontrada !"));

        venda.setNomeProduto(request.getNomeProduto());
        venda.setPreco(request.getPreco());
        venda.setTotal(request.getTotal());
        venda.setQuantidade(request.getQuantidade());
        venda.setFormaPagamento(request.getFormaPagamento());
        if (request.getDataVenda() != null) {
            venda.setCreateAt(request.getDataVenda());
        }

        return new VendasResponse().toModelResponse(repository.save(venda));
    }

    public boolean deleteVenda(UUID idVenda) {
        Vendas venda = repository.findById(idVenda)
                .orElseThrow(() -> new EntityNotFound("Venda não encontrada !"));

        if (venda.getProduto() != null) {
            Produto produto = venda.getProduto();
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + venda.getQuantidade());
            produtoRepository.save(produto);
            log.info("Venda excluída | id: {} | estoque devolvido: {} unidades ao produto: {}",
                    idVenda, venda.getQuantidade(), produto.getNomeProduto());
        } else {
            log.info("Venda excluída | id: {}", idVenda);
        }

        repository.delete(venda);
        return true;
    }
}
