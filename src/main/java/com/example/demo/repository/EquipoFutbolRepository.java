package com.example.demo.repository;

import com.example.demo.model.EquipoFutbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoFutbolRepository extends JpaRepository<EquipoFutbol, Long> {
    List<EquipoFutbol> findByLiga(String liga);
    List<EquipoFutbol> findByCiudad(String ciudad);
} 