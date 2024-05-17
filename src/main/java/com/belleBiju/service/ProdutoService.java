package com.belleBiju.service;

import com.belleBiju.DTOs.requests.ProdutoRequest;
import com.belleBiju.DTOs.responses.ProdutoResponse;
import com.belleBiju.entities.Produto;
import com.belleBiju.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {


    private final ProdutoRepository repository;

    ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoResponse salvarNovoProduto(ProdutoRequest request) {

        Produto produto = new Produto();

        produto.setNomeProduto(request.getNomeProduto());
        produto.setPrecoProduto(request.getPrecoProduto());

        return new ProdutoResponse(repository.save(produto));

    }

    public List<ProdutoResponse> listarTodosProdutos() {


        List<Produto> listagem = repository.findAll();

        List<ProdutoResponse> retorno = new ArrayList<>();

        for(Produto listaRetorno : listagem) {
            retorno.add(new ProdutoResponse(listaRetorno));
        }

        return retorno;
    }
}
