package com.belleBiju.repository;

import com.belleBiju.entities.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, UUID> {

    @Query(value = "SELECT * FROM tbl_vendas WHERE DATE(create_at) = CURRENT_DATE ORDER BY create_at DESC", nativeQuery = true)
    List<Vendas> findAllDesc();

    @Query(value = "SELECT * FROM tbl_vendas WHERE DATE(create_at) BETWEEN TO_DATE(:inicio, 'YYYY-MM-DD') AND TO_DATE(:fim, 'YYYY-MM-DD')", nativeQuery = true)
    List<Vendas> findByVendasWhereDate(@Param("inicio") String inicio, @Param("fim") String fim);

}
