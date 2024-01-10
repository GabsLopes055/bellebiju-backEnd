package com.belleBiju.service;

import com.belleBiju.DTOs.requests.VendasRequest;
import com.belleBiju.DTOs.responses.VendasResponse;
import com.belleBiju.entities.Vendas;
import com.belleBiju.repository.VendasRepository;
import org.springframework.stereotype.Service;

@Service
public class VendasService {

    private final VendasRepository repository;

    private VendasService(VendasRepository repository) {
        this.repository = repository;
    }

    /*Metodo para salvar uma venda*/
    public VendasResponse createVenda(VendasRequest request){

        VendasRequest response = new VendasRequest();

        Vendas vendas = repository.save(response.toModel(request));

        return new VendasResponse().toModelResponse(vendas);
    }

}
