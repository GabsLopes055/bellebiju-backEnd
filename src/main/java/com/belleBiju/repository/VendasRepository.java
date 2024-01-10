package com.belleBiju.repository;

import com.belleBiju.entities.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, UUID> {
}
