package com.example.demo.repository;

import com.example.demo.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.param.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    List<Jugador> findByEquipo(String equipo);
    List<Jugador> findByPosicion(String posicion);

    @Query("SELECT j FROM Jugador j WHERE " +
           "LOWER(j.nombre) LIKE LOWER(CONCAT('%', :patron, '%')) OR " +
           "LOWER(j.apellidos) LIKE LOWER(CONCAT('%', :patron, '%')) OR " +
           "LOWER(j.equipo) LIKE LOWER(CONCAT('%', :patron, '%')) OR " +
           "LOWER(j.posicion) LIKE LOWER(CONCAT('%', :patron, '%'))")
    List<Jugador> buscarPorPatron(@Param("patron") String patron);
} 