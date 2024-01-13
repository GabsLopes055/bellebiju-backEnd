package com.belleBiju.repository;

import com.belleBiju.entities.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, UUID> {

    @Query(value = "SELECT * FROM tbl_vendas ORDER BY id DESC", nativeQuery = true)
    List<Vendas> findAllDesc();

}
