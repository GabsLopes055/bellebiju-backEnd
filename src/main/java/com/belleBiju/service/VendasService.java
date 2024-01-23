package com.belleBiju.service;

import com.belleBiju.DTOs.requests.VendasRequest;
import com.belleBiju.DTOs.responses.VendasResponse;
import com.belleBiju.entities.Vendas;
import com.belleBiju.repository.VendasRepository;
import com.belleBiju.service.exceptions.EntityNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VendasService {

    private final VendasRepository repository;

    private VendasService(VendasRepository repository) {
        this.repository = repository;
    }

    /*Metodo para salvar uma venda*/
    public VendasResponse createVenda(VendasRequest request) {

        VendasRequest response = new VendasRequest();

        Vendas vendas = repository.save(response.toModel(request));

        return new VendasResponse().toModelResponse(vendas);
    }

    /*Metodo para listar todas as vendas*/
    public List<VendasResponse> listAllVendas() {

        List<Vendas> listVendas = repository.findAllDesc();

        List<VendasResponse> responseVendas = new ArrayList<>();

        for (Vendas response : listVendas) {
            VendasResponse vendasResponse = new VendasResponse().toModelResponse(response);
            responseVendas.add(vendasResponse);
        }

        return responseVendas;


    }

    public List<VendasResponse> listVendasByDates(String inicio, String fim) {

        System.out.println(inicio + fim);

        List<Vendas> listVendas = repository.findByVendasWhereDate(inicio, fim);

        List<VendasResponse> responseVendas = new ArrayList<>();

        for (Vendas response : listVendas) {
            VendasResponse vendasResponse = new VendasResponse().toModelResponse(response);
            responseVendas.add(vendasResponse);
        }

        return responseVendas;
    }

    public VendasResponse editVenda(VendasRequest request, UUID idVenda) {

        Optional<Vendas> findVenda = Optional.ofNullable(repository.findById(idVenda).orElseThrow(() -> new EntityNotFound("Venda não encontrada !")));

        Vendas venda = findVenda.get();

        venda.setNomeProduto(request.getNomeProduto());
        venda.setPreco(request.getPreco());
        venda.setTotal(request.getTotal());
        venda.setQuantidade(request.getQuantidade());
        venda.setFormaPagamento(request.getFormaPagamento());

        this.repository.save(venda);

        return new VendasResponse().toModelResponse(venda);

    }

    public boolean deleteVenda(UUID idVenda) {

        Optional<Vendas> findVenda = Optional.ofNullable(repository.findById(idVenda).orElseThrow(() -> new EntityNotFound("Venda não encontrada !")));

        if (findVenda.isEmpty()) return false;

        this.repository.delete(findVenda.get());
        return true;

    }


}
