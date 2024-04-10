package com.belleBiju.repository;

import com.belleBiju.DTOs.responses.GraficoPizzaResponse;
import com.belleBiju.entities.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, UUID> {

    public interface graficoPizza {
        int getDinheiro();
        int getCredito();
        int getDebito();
        int getPix();
    }

    @Query(value = "SELECT * FROM tbl_vendas WHERE DATE(create_at) = CURRENT_DATE ORDER BY create_at DESC", nativeQuery = true)
    List<Vendas> findAllDesc();

    @Query(value = "SELECT * FROM tbl_vendas WHERE DATE(create_at) BETWEEN TO_DATE(:inicio, 'YYYY-MM-DD') AND TO_DATE(:fim, 'YYYY-MM-DD')", nativeQuery = true)
    List<Vendas> findByVendasWhereDate(@Param("inicio") String inicio, @Param("fim") String fim);

    @Query(value = "SELECT SUM(case when FORMA_PAGAMENTO = 0 then 1 else 0 end) as DINHEIRO,\n" +
            "            SUM(case when FORMA_PAGAMENTO = 1 then 1 else 0 end) as PIX,\n" +
            "            SUM(case when FORMA_PAGAMENTO = 2 then 1 else 0 end) as DEBITO,\n" +
            "            SUM(case when FORMA_PAGAMENTO = 3 then 1 else 0 end) as CREDITO\n" +
            "            from tbl_vendas WHERE DATE(create_at) BETWEEN :inicio AND :fim", nativeQuery = true)
    Object gerarGraficoPizza(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query(value = "SELECT\n" +
            "(\n" +
            "\tSELECT SUM(total) FROM tbl_vendas WHERE forma_pagamento = 0 AND DATE(create_at) BETWEEN :inicio AND :fim\n" +
            ") AS \"DINHEIRO\",\n" +
            "(\n" +
            "\tSELECT SUM(total) FROM tbl_vendas WHERE forma_pagamento = 1 AND DATE(create_at) BETWEEN :inicio AND :fim\n" +
            ") AS \"PIX\",\n" +
            "(\n" +
            "\tSELECT SUM(total) FROM tbl_vendas WHERE forma_pagamento = 2 AND DATE(create_at) BETWEEN :inicio AND :fim\n" +
            ") AS \"DEBITO\",\n" +
            "(\n" +
            "\tSELECT SUM(total) FROM tbl_vendas WHERE forma_pagamento = 3 AND DATE(create_at) BETWEEN :inicio AND :fim\n" +
            ") AS \"CREDITO\"", nativeQuery = true)
    Object gerarGraficoTotalVendas(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

}
