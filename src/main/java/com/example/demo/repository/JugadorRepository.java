package com.example.demo.repository;

import com.example.demo.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    List<Jugador> findByEquipo(String equipo);
    List<Jugador> findByPosicion(String posicion);
} 