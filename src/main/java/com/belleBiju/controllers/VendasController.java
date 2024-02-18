package com.belleBiju.controllers;

import com.belleBiju.DTOs.requests.VendasRequest;
import com.belleBiju.DTOs.responses.VendasResponse;
import com.belleBiju.service.VendasService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/vendas")
@CrossOrigin("*")
public class VendasController {


    @Autowired
    private VendasService service;

    /*Metodo para salvar uma venda*/
    @PostMapping
    public ResponseEntity<VendasResponse> saveVenda(@RequestBody @Valid VendasRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createVenda(request));

    }

    /*Metodo para retornar todas as vendas realizadas*/
    @GetMapping
    public ResponseEntity<List<VendasResponse>> listAllVendas() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAllVendas());
    }

    /*Metodo para pesquisar por duas datas*/
    @PostMapping("/{inicio}/{fim}")
    public ResponseEntity<List<VendasResponse>> listAllVendasByDates(@PathVariable(value = "inicio") String inicio, @PathVariable(value = "fim") String fim) {
        return ResponseEntity.status(HttpStatus.OK).body(service.listVendasByDates(inicio,fim));
    }


    @PutMapping("/{idVenda}")
    public ResponseEntity<VendasResponse> editVenda(@PathVariable(value = "idVenda") UUID idVenda, @RequestBody @Valid VendasRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.editVenda(request, idVenda));
    }

    @DeleteMapping("/{idVenda}")
    public ResponseEntity<String> deleteVenda(@PathVariable(value = "idVenda") UUID idVenda) {
        if(this.service.deleteVenda(idVenda)) return ResponseEntity.status(HttpStatus.OK).body("Venda Deletada");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao Excluir Venda !");
    }


}
