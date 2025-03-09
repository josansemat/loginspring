package com.example.demo.repository;

import com.example.demo.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    List<Jugador> findByEquipo(String equipo);
    List<Jugador> findByPosicion(String posicion);
    
    // Métodos de búsqueda adicionales
    List<Jugador> findByNombreContainingIgnoreCase(String nombre);
    List<Jugador> findByApellidosContainingIgnoreCase(String apellidos);
    
    @Query("SELECT j FROM Jugador j WHERE " +
           "LOWER(j.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) OR " +
           "LOWER(j.apellidos) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Jugador> buscarPorNombreOApellidos(@Param("busqueda") String busqueda);
    
    @Query("SELECT j FROM Jugador j WHERE " +
           "(:nombre IS NULL OR LOWER(j.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
           "(:posicion IS NULL OR j.posicion = :posicion) AND " +
           "(:equipo IS NULL OR LOWER(j.equipo) LIKE LOWER(CONCAT('%', :equipo, '%')))")
    List<Jugador> busquedaAvanzada(
            @Param("nombre") String nombre, 
            @Param("posicion") String posicion,
            @Param("equipo") String equipo);
} 