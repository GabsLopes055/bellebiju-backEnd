package com.belleBiju.service;

import com.belleBiju.DTOs.responses.ProdutoMaisVendidoResponse;
import com.belleBiju.DTOs.responses.ResumoFinanceiroResponse;
import com.belleBiju.DTOs.responses.ResumoPorCategoriaResponse;
import com.belleBiju.repository.VendasRepository;
import com.belleBiju.repository.VendasRepository.ProdutoMaisVendidoProjection;
import com.belleBiju.repository.VendasRepository.ResumoFinanceiroProjection;
import com.belleBiju.repository.VendasRepository.ResumoPorCategoriaProjection;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final VendasRepository repository;

    RelatorioService(VendasRepository repository) {
        this.repository = repository;
    }

    public ResumoFinanceiroResponse gerarResumo(String dataInicio, String dataFim, UUID idCategoria) {
        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);

        if (idCategoria != null) {
            ResumoFinanceiroProjection projection = repository.findResumoFinanceiroByCategoria(
                    inicio, fim, idCategoria.toString());
            return new ResumoFinanceiroResponse(dataInicio, dataFim,
                    projection.getTotalVendas(), projection.getReceitaTotal(), projection.getTicketMedio());
        }

        ResumoFinanceiroProjection projection = repository.findResumoFinanceiro(inicio, fim);
        return new ResumoFinanceiroResponse(dataInicio, dataFim,
                projection.getTotalVendas(), projection.getReceitaTotal(), projection.getTicketMedio());
    }

    public List<ProdutoMaisVendidoResponse> produtosMaisVendidos(String dataInicio, String dataFim, UUID idCategoria) {
        LocalDate inicio = LocalDate.parse(dataInicio);
        LocalDate fim = LocalDate.parse(dataFim);

        List<ProdutoMaisVendidoProjection> projections = idCategoria != null
                ? repository.findProdutosMaisVendidosByCategoria(inicio, fim, idCategoria.toString())
                : repository.findProdutosMaisVendidos(inicio, fim);

        return projections.stream()
                .map(ProdutoMaisVendidoResponse::new)
                .collect(Collectors.toList());
    }

    public List<ResumoPorCategoriaResponse> resumoPorCategoria(String dataInicio, String dataFim) {
        List<ResumoPorCategoriaProjection> projections = repository.findResumoPorCategoria(
                LocalDate.parse(dataInicio),
                LocalDate.parse(dataFim)
        );
        return projections.stream()
                .map(p -> new ResumoPorCategoriaResponse(
                        UUID.fromString(p.getIdCategoria()),
                        p.getNomeCategoria(),
                        p.getTotalVendas(),
                        p.getReceitaTotal(),
                        p.getTicketMedio()
                ))
                .collect(Collectors.toList());
    }
}
