package com.belleBiju.DTOs.responses;

import org.springframework.data.domain.Page;

import java.util.List;

public class PaginaResponse<T> {

    private List<T> conteudo;
    private int paginaAtual;
    private int totalPaginas;
    private long totalElementos;
    private int tamanhoPagina;

    public PaginaResponse(Page<T> page) {
        this.conteudo = page.getContent();
        this.paginaAtual = page.getNumber();
        this.totalPaginas = page.getTotalPages();
        this.totalElementos = page.getTotalElements();
        this.tamanhoPagina = page.getSize();
    }

    public List<T> getConteudo() { return conteudo; }
    public int getPaginaAtual() { return paginaAtual; }
    public int getTotalPaginas() { return totalPaginas; }
    public long getTotalElementos() { return totalElementos; }
    public int getTamanhoPagina() { return tamanhoPagina; }
}
