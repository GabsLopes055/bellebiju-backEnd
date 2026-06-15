package com.belleBiju.repository;

import com.belleBiju.entities.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, UUID> {

    interface graficoPizza {
        int getDinheiro();
        int getCredito();
        int getDebito();
        int getPix();
    }

    interface ResumoFinanceiroProjection {
        Long getTotalVendas();
        Double getReceitaTotal();
        Double getTicketMedio();
    }

    interface ProdutoMaisVendidoProjection {
        String getNomeProduto();
        Long getQuantidadeTotal();
        Double getReceitaTotal();
    }

    interface ResumoPorCategoriaProjection {
        String getIdCategoria();
        String getNomeCategoria();
        Long getTotalVendas();
        Double getReceitaTotal();
        Double getTicketMedio();
    }

    @Query(value = "SELECT * FROM tbl_vendas WHERE DATE(create_at) = CURRENT_DATE ORDER BY create_at DESC",
           countQuery = "SELECT COUNT(*) FROM tbl_vendas WHERE DATE(create_at) = CURRENT_DATE",
           nativeQuery = true)
    Page<Vendas> findAllDesc(Pageable pageable);

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

    @Query(value = "SELECT COUNT(*) as totalVendas, " +
            "COALESCE(SUM(total), 0) as receitaTotal, " +
            "COALESCE(AVG(total), 0) as ticketMedio " +
            "FROM tbl_vendas WHERE DATE(create_at) BETWEEN :inicio AND :fim", nativeQuery = true)
    ResumoFinanceiroProjection findResumoFinanceiro(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query(value = "SELECT nome_produto as nomeProduto, " +
            "SUM(quantidade) as quantidadeTotal, " +
            "SUM(total) as receitaTotal " +
            "FROM tbl_vendas " +
            "WHERE DATE(create_at) BETWEEN :inicio AND :fim " +
            "GROUP BY nome_produto " +
            "ORDER BY SUM(quantidade) DESC", nativeQuery = true)
    List<ProdutoMaisVendidoProjection> findProdutosMaisVendidos(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    @Query(value = "SELECT v.nome_produto as nomeProduto, " +
            "SUM(v.quantidade) as quantidadeTotal, " +
            "SUM(v.total) as receitaTotal " +
            "FROM tbl_vendas v " +
            "JOIN tbl_produto p ON v.id_produto = p.id_produto " +
            "WHERE DATE(v.create_at) BETWEEN :inicio AND :fim " +
            "AND p.id_categoria = CAST(:idCategoria AS uuid) " +
            "GROUP BY v.nome_produto " +
            "ORDER BY SUM(v.quantidade) DESC", nativeQuery = true)
    List<ProdutoMaisVendidoProjection> findProdutosMaisVendidosByCategoria(
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim,
            @Param("idCategoria") String idCategoria);

    @Query(value = "SELECT COUNT(*) as totalVendas, " +
            "COALESCE(SUM(v.total), 0) as receitaTotal, " +
            "COALESCE(AVG(v.total), 0) as ticketMedio " +
            "FROM tbl_vendas v " +
            "JOIN tbl_produto p ON v.id_produto = p.id_produto " +
            "WHERE DATE(v.create_at) BETWEEN :inicio AND :fim " +
            "AND p.id_categoria = CAST(:idCategoria AS uuid)", nativeQuery = true)
    ResumoFinanceiroProjection findResumoFinanceiroByCategoria(
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim,
            @Param("idCategoria") String idCategoria);

    @Query(value = "SELECT CAST(c.id_categoria AS varchar) as idCategoria, " +
            "c.nome_categoria as nomeCategoria, " +
            "COUNT(*) as totalVendas, " +
            "COALESCE(SUM(v.total), 0) as receitaTotal, " +
            "COALESCE(AVG(v.total), 0) as ticketMedio " +
            "FROM tbl_vendas v " +
            "JOIN tbl_produto p ON v.id_produto = p.id_produto " +
            "JOIN tbl_categoria c ON p.id_categoria = c.id_categoria " +
            "WHERE DATE(v.create_at) BETWEEN :inicio AND :fim " +
            "GROUP BY c.id_categoria, c.nome_categoria " +
            "ORDER BY SUM(v.total) DESC", nativeQuery = true)
    List<ResumoPorCategoriaProjection> findResumoPorCategoria(
            @Param("inicio") LocalDate inicio,
            @Param("fim") LocalDate fim);
}
